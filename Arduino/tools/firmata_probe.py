#!/usr/bin/env python3
"""Minimal Firmata host: handshake, capability dump, LED blink, analog read."""
import sys
import time

import serial

PORT = sys.argv[1] if len(sys.argv) > 1 else "/dev/ttyACM0"
BAUD = int(sys.argv[2]) if len(sys.argv) > 2 else 115200

MODES = {0: "IN", 1: "OUT", 2: "ANALOG", 3: "PWM", 4: "SERVO", 6: "I2C",
         7: "ONEWIRE", 8: "STEPPER", 10: "SERIAL", 11: "PULLUP", 12: "SPI",
         15: "DHT", 16: "FREQ", 0x7F: "IGNORE"}


def read_messages(ser, seconds):
    """Collect parsed Firmata messages for `seconds`."""
    buf = bytearray()
    end = time.time() + seconds
    while time.time() < end:
        buf += ser.read(ser.in_waiting or 1)
    msgs, i = [], 0
    while i < len(buf):
        b = buf[i]
        if b == 0xF0:
            j = buf.find(0xF7, i)
            if j < 0:
                break
            msgs.append(("sysex", buf[i + 1], bytes(buf[i + 2:j])))
            i = j + 1
        elif b == 0xF9 and i + 2 < len(buf):
            msgs.append(("version", buf[i + 1], buf[i + 2]))
            i += 3
        elif 0xE0 <= b <= 0xEF and i + 2 < len(buf):
            msgs.append(("analog", b & 0x0F, buf[i + 1] | buf[i + 2] << 7))
            i += 3
        elif 0x90 <= b <= 0x9F and i + 2 < len(buf):
            msgs.append(("digital", b & 0x0F, buf[i + 1] | buf[i + 2] << 7))
            i += 3
        else:
            msgs.append(("junk", b, None))
            i += 1
    return msgs


LED_MATRIX = 0x01  # user-defined sysex, see sketches/ConfigurableFirmataR4


def pack_frame(leds):
    """96 LED booleans -> 14 bytes, 7 bits each (LED i = byte i//7, bit i%7)."""
    out = bytearray((len(leds) + 6) // 7)
    for i, on in enumerate(leds):
        if on:
            out[i // 7] |= 1 << (i % 7)
    return bytes(out)


def query_matrix(ser):
    """Return (cols, rows, leds) or None if the firmware has no LED matrix."""
    ser.write(bytes([0xF0, LED_MATRIX, 0x01, 0xF7]))
    for m in read_messages(ser, 0.5):
        if m[0] == "sysex" and m[1] == LED_MATRIX and m[2][:1] == b"\x02":
            cols, rows, packed = m[2][1], m[2][2], m[2][3:]
            leds = [bool(packed[i // 7] >> (i % 7) & 1) for i in range(cols * rows)]
            return cols, rows, leds
    return None


def test_matrix(ser):
    """Checkerboard, read back, toggle one LED, clear. Returns True/False/None."""
    found = query_matrix(ser)
    if found is None:
        print("LED matrix: not present")
        return None
    cols, rows, _ = found
    checker = [(i // cols + i % cols) % 2 == 0 for i in range(cols * rows)]
    ser.write(bytes([0xF0, LED_MATRIX, 0x00]) + pack_frame(checker) + bytes([0xF7]))
    time.sleep(0.5)
    ok = query_matrix(ser)[2] == checker
    ser.write(bytes([0xF0, LED_MATRIX, 0x03, 0, 0, 0xF7]))  # LED 0 off
    expected = [False] + checker[1:]
    ok = ok and query_matrix(ser)[2] == expected
    time.sleep(0.5)
    ser.write(bytes([0xF0, LED_MATRIX, 0x00]) + pack_frame([False] * cols * rows) + bytes([0xF7]))
    ok = ok and not any(query_matrix(ser)[2])
    print(f"LED matrix: {cols}x{rows}, checkerboard/pixel/clear readback {'ok' if ok else 'MISMATCH'}")
    return ok


def main():
    ok = True
    is_configurable = False
    with serial.Serial(PORT, BAUD, timeout=0.05) as ser:
        time.sleep(2.0)
        ser.reset_input_buffer()
        ser.write(bytes([0xF9, 0xF0, 0x79, 0xF7]))  # version + firmware
        ser.write(bytes([0xF0, 0x6B, 0xF7]))        # capability query
        ser.write(bytes([0xF0, 0x69, 0xF7]))        # analog mapping
        msgs = read_messages(ser, 2.0)

        kinds = {m[0] for m in msgs}
        for m in msgs:
            if m[0] == "version":
                print(f"protocol version: {m[1]}.{m[2]}")
            elif m[0] == "sysex" and m[1] == 0x79:
                d = m[2]
                name = bytes(d[k] | d[k + 1] << 7 for k in range(2, len(d) - 1, 2)).decode()
                print(f"firmware: {name} {d[0]}.{d[1]}")
                is_configurable = name.startswith("ConfigurableFirmata")
            elif m[0] == "sysex" and m[1] == 0x6C:
                pins, cur = [], []
                d = m[2]
                k = 0
                while k < len(d):
                    if d[k] == 0x7F:
                        pins.append(cur)
                        cur = []
                        k += 1
                    else:
                        cur.append(MODES.get(d[k], str(d[k])))
                        k += 2
                print(f"capabilities: {len(pins)} pins")
                for n, p in enumerate(pins):
                    print(f"  pin {n:2}: {' '.join(p) or '-'}")
                pwm = [n for n, p in enumerate(pins) if "PWM" in p]
                ana = [n for n, p in enumerate(pins) if "ANALOG" in p]
                print(f"  PWM pins: {pwm}\n  analog pins: {ana}")
            elif m[0] == "sysex" and m[1] == 0x6A:
                amap = {n: a for n, a in enumerate(m[2]) if a != 0x7F}
                print(f"analog mapping: {amap}")
        junk = [m[1] for m in msgs if m[0] == "junk"]
        if junk:
            print(f"unparsed bytes: {len(junk)}")
        for want in ("version",):
            if want not in kinds:
                print(f"FAIL: no {want} reply")
                ok = False
        if not any(m[0] == "sysex" and m[1] == 0x6C for m in msgs):
            print("FAIL: no capability response")
            ok = False

        # Blink the built-in LED on pin 13
        ser.write(bytes([0xF4, 13, 1]))  # set pin mode OUTPUT
        for v in (1, 0, 1, 0):
            ser.write(bytes([0xF5, 13, v]))  # set digital pin value
            time.sleep(0.3)
        print("LED on pin 13 blinked twice")

        # Enable analog reporting on A0 and read a few samples
        ser.write(bytes([0xC0, 1]))
        samples = [m[2] for m in read_messages(ser, 1.0) if m[0] == "analog" and m[1] == 0]
        ser.write(bytes([0xC0, 0]))
        print(f"A0 samples: {samples[:10]} ({len(samples)} total)")
        if not samples:
            print("FAIL: no analog reports")
            ok = False

        # Drive PWM on pin 3 and read the pin state back
        ser.write(bytes([0xF4, 3, 3]))                      # pin 3 -> PWM
        ser.write(bytes([0xE3, 128 & 0x7F, 128 >> 7]))      # analog write 128
        ser.write(bytes([0xF0, 0x6D, 3, 0xF7]))             # pin state query
        st = [m[2] for m in read_messages(ser, 0.5) if m[0] == "sysex" and m[1] == 0x6E]
        if st:
            d = st[0]
            val = sum(b << (7 * k) for k, b in enumerate(d[2:]))
            print(f"pin 3 state: mode={MODES.get(d[1], d[1])} value={val}")
            # ConfigurableFirmata does not record PWM values in its pin state
            if d[1] != 3 or (val != 128 and not is_configurable):
                print("FAIL: PWM on pin 3 not applied")
                ok = False
        else:
            print("FAIL: no pin state response")
            ok = False
        ser.write(bytes([0xE3, 0, 0]))

        if test_matrix(ser) is False:
            print("FAIL: LED matrix readback")
            ok = False

    print("RESULT:", "PASS" if ok else "FAIL")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main())
