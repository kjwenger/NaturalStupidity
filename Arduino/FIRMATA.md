# Firmata on the Arduino UNO R4 WiFi

Status as of 2026-10-09: what upstream ships, what actually broke on the UNO R4,
and where the copies in this directory stand.

## Table of Contents

- [Quick Start from a Fresh Clone](#quick-start-from-a-fresh-clone)
- [Hardware](#hardware)
- [Upstream Versions](#upstream-versions)
- [The UNO R4 Problem](#the-uno-r4-problem)
- [Is the ESP32-S3 Serial Bridge to Blame?](#is-the-esp32-s3-serial-bridge-to-blame)
- [The Remaining Bug: PWM Over-Reporting](#the-remaining-bug-pwm-over-reporting)
- [Solution](#solution)
- [Local Copies in This Directory](#local-copies-in-this-directory)
- [Verification](#verification)
- [LED Matrix Support](#led-matrix-support)
- [Firmata Test GUI](#firmata-test-gui) ([Testing Guide](#testing-guide))
- [Work Log](#work-log)
- [Sources](#sources)

## Quick Start from a Fresh Clone

Tested on Ubuntu with an UNO R4 WiFi on `/dev/ttyACM0`. The versions below are
the ones verified on 2026-10-09, pinned so that later releases can't change the
results.

```bash
# 1. Clone, then initialize only the two Arduino submodules
git clone https://github.com/kjwenger/NaturalStupidity.git
cd NaturalStupidity
git submodule update --init Arduino/ConfigurableFirmata Arduino/tools/firmata_test
cd Arduino

# 2. Apply the local fixes to the submodules
git -C ConfigurableFirmata apply ../patches/ConfigurableFirmata-unor4-pwm-pins.patch
git -C tools/firmata_test apply ../../patches/firmata_test-linux-unor4.patch

# 3. One-time machine setup (log out and back in afterwards for dialout)
sudo usermod -aG dialout "$USER"
sudo apt-get install -y libwxgtk3.2-dev build-essential python3-serial
curl -fsSL https://raw.githubusercontent.com/arduino/arduino-cli/master/install.sh \
  | BINDIR=$HOME/.local/bin sh -s 1.5.1
arduino-cli core update-index
arduino-cli core install arduino:renesas_uno@1.6.0
arduino-cli lib install --no-deps "Adafruit Unified Sensor@1.1.15" \
  "DHT sensor library@1.4.7" "Servo@1.3.0"

# 4. Flash ConfigurableFirmata with LED matrix support (115200 baud)
#    and run the automated check
arduino-cli compile --upload -p /dev/ttyACM0 --fqbn arduino:renesas_uno:unor4wifi \
  --library ConfigurableFirmata sketches/ConfigurableFirmataR4
tools/firmata_probe.py /dev/ttyACM0 115200    # ends with "RESULT: PASS"

# 5. Build and start the GUI, then follow the Testing Guide
make -C tools/firmata_test WXCONFIG=wx-config
tools/firmata_test/firmata_test
```

Only one program can hold `/dev/ttyACM0` at a time. Close firmata_test (or any
serial monitor) before uploading or running the probe, or the upload fails with
`Failed uploading: uploading error: exit status 1`.

`sketches/ConfigurableFirmataR4` is the stock ConfigurableFirmata example plus
the [LED matrix feature](#led-matrix-support). For the plain example, flash
`ConfigurableFirmata/examples/ConfigurableFirmata` instead. For StandardFirmata,
flash with `--library Firmata Firmata/examples/StandardFirmata` and use 57600
baud (probe and GUI). The
`Firmata/` copy is committed with its fix, so it needs no patch. Without step 2,
the ConfigurableFirmata build still works but advertises PWM on the wrong pins
(see [The Remaining Bug](#the-remaining-bug-pwm-over-reporting)).

## Hardware

The board attached to the development machine:

| Property | Value |
|---|---|
| Board | Arduino UNO R4 WiFi (ABX00087) |
| USB ID | `2341:1002` (Arduino SA, "UNO WiFi R4 CMSIS-DAP") |
| Serial port | `/dev/ttyACM0` |
| Stable path | `/dev/serial/by-id/usb-Arduino_UNO_WiFi_R4_CMSIS-DAP_F412FA750980-if01` |
| FQBN | `arduino:renesas_uno:unor4wifi` |

The board has two processors:

- **Renesas RA4M1** (Arm Cortex-M4, 48 MHz) is the main MCU. Sketches, Firmata
  included, run here.
- **ESP32-S3-MINI-1** handles WiFi and Bluetooth LE. With Arduino's stock bridge
  firmware it also sits between the USB-C connector and the RA4M1, acting as the
  USB-to-serial adapter and CMSIS-DAP debugger. That is why the board enumerates
  as "CMSIS-DAP". The RA4M1 reaches the ESP32 (and through it, USB) over a UART,
  and sketches see that UART as `Serial`.

The ESP32 firmware can be updated with Arduino's `unor4wifi-updater`. Flashing
custom ESP32 firmware breaks the USB bridge until the stock firmware is restored.

## Upstream Versions

| Library | Latest release | Development branch |
|---|---|---|
| [Firmata](https://github.com/firmata/arduino) (StandardFirmata) | **2.5.9**, 2022-12-14. This is what the Library Manager installs, and it has **no UNO R4 support**. | R4 support was merged in PR #509 (2023-09) and PR #517 (2024-09) but never released. PR #520, the R4 WiFi PWM/ADC pin-detection fix, has been **open since 2025-11**. |
| [ConfigurableFirmata](https://github.com/firmata/ConfigurableFirmata) | **3.3.0**, tagged 2024-10-06. The last formal GitHub release is 3.2.0 (2023-09). | `library.properties` says **3.4.0** (untagged). It includes PR #168 (R4 build fix, 2024-10) and PR #182 (R4 interrupt fix, 2025-10). |

## The UNO R4 Problem

Every documented failure traces back to `Boards.h`, not to the serial link:

1. **No board entry in released Firmata.** Firmata 2.5.9 has no
   `ARDUINO_UNOR4_MINIMA` / `ARDUINO_UNOR4_WIFI` section, so it can't target the
   board at all without a patched `Boards.h`.
2. **Macro name clash with the Renesas core.** The R4 core defines its own
   `IS_PIN_PWM` and `IS_PIN_ANALOG` macros. Firmata's `Boards.h` defines
   macros with the same names. The first R4 support attempted:

   ```c
   #undef IS_PIN_PWM
   #define IS_PIN_PWM(p)           digitalPinHasPWM(p)
   ```

   But the core's `digitalPinHasPWM()` is itself defined in terms of the core's
   `IS_PIN_PWM`, so after the `#undef` it expands into Firmata's macro instead.
   Depending on the core version, the result is a build error
   ([ConfigurableFirmata #167](https://github.com/firmata/ConfigurableFirmata/issues/167))
   or wrong PWM/analog capability reports. A host such as vvvv uses those reports
   during its handshake and may never reach "Firmata ready".
3. **The fix** copies the core's definition under a non-colliding name:

   ```c
   #undef IS_PIN_PWM
   #undef IS_PIN_ANALOG
   #define IS_PIN_ANALOG(p)        ((p) >= 14 && (p) < 14 + TOTAL_ANALOG_PINS)
   #define ARDUINO_IS_PIN_PWM(x)   (((x & PIN_USE_MASK) ==  PIN_PWM_GPT) || ((x & PIN_USE_MASK) ==  PIN_PWM_AGT))
   #define ARDUINO_digitalPinHasPWM(p) (ARDUINO_IS_PIN_PWM(getPinCfgs(p, PIN_CFG_REQ_PWM)[0]))
   #define IS_PIN_PWM(p)           ARDUINO_digitalPinHasPWM(p)
   ```

   This came out of the Arduino Forum thread (2025-06) and is what Firmata PR #520
   proposes. ConfigurableFirmata solved the same clash in PR #168 by renaming its
   macros (`FIRMATA_IS_PIN_PWM`, `FIRMATA_IS_PIN_ANALOG`).

## Is the ESP32-S3 Serial Bridge to Blame?

No evidence says so. An earlier analysis here claimed that `Serial` on the R4 WiFi
"goes through the ESP32 bridge which isn't properly configured", but:

- None of the upstream issues, PRs, or forum threads names the ESP32 bridge as a
  cause.
- `Serial` on the R4 WiFi is an ordinary UART from the sketch's point of view.
  Other serial sketches work through the bridge without special setup.
- The same failures were reported on the **R4 Minima**, which has no ESP32.

A forum user reported in 2025-05 that ConfigurableFirmata 3.3.0 worked on the
Minima but **not** on the WiFi, and the thread never resolved why. A test on the
attached board (2026-10-09, renesas_uno core 1.6.0) settles it for current code.
Both StandardFirmata and ConfigurableFirmata 3.4 complete the full handshake
(version, firmware, capabilities, analog mapping), drive digital outputs, and
stream analog reports through the stock ESP32-S3 bridge. **The bridge works.** See
[Verification](#verification).

## The Remaining Bug: PWM Over-Reporting

With the build fixes in place, one defect remained in **both** libraries. They
advertised PWM on 16 pins: 0–13, 18 and 19. Pins 0 and 1 even showed up as
"PWM only", although they are `Serial1` and excluded from digital I/O. Both
libraries passed the core's `digitalPinHasPWM()` through unfiltered, and on the
RA4M1 that returns true for every pin with a GPT/AGT timer channel in the pin mux
table. A host that trusts the capability response offers PWM on pins the board
doesn't label as PWM.

## Solution

On the R4, advertise PWM only on the six pins labelled `~` on the board: 3, 5,
6, 9, 10 and 11. This is the same set as the UNO R3, so R3-era host patches
(vvvv included) keep working, and it drops the dependency on the core's internal
`IS_PIN_PWM` / `PIN_PWM_GPT` macros that caused the original build breakage.

- **`Firmata/Boards.h`**: `IS_PIN_PWM(p)` is now the explicit pin list. It
  replaces the "25.03." `ARDUINO_digitalPinHasPWM` workaround.
- **`ConfigurableFirmata/`**: the same change to `FIRMATA_IS_PIN_PWM(p)` in
  `src/utility/Boards.h`. The submodule tracks upstream, so the change lives in
  [`patches/ConfigurableFirmata-unor4-pwm-pins.patch`](patches/ConfigurableFirmata-unor4-pwm-pins.patch).
  Re-apply it after a fresh clone or a submodule update:

  ```bash
  git -C ConfigurableFirmata apply ../patches/ConfigurableFirmata-unor4-pwm-pins.patch
  ```

## Local Copies in This Directory

| Path | Version | R4 state |
|---|---|---|
| `Firmata/` | 2.5.9 plus development-branch R4 support | Builds and works on the R4 WiFi with the PWM fix above. `Boards_h.old` / `Boards_h.new` are earlier edit snapshots. |
| `ConfigurableFirmata/` | Git submodule on upstream head, 3.4.0 | Includes PR #155, #168 and #182. Works on the R4 WiFi once the patch in `patches/` is applied. |
| `Firmata_README.md` | Instructions in German | Describes a Firmata **2.5.7** with an extended `Boards.h` that works on the UNO R3, R4 Minima and R4 WiFi with vvvv beta and gamma as host. |

## Verification

Setup: as in the [Quick Start](#quick-start-from-a-fresh-clone): `arduino-cli`
1.5.1, the `arduino:renesas_uno` 1.6.0 core, and the `Servo` 1.3.0,
`DHT sensor library` 1.4.7 and `Adafruit Unified Sensor` 1.1.15 libraries. The
user must be in the `dialout` group to open `/dev/ttyACM0`. Membership takes
effect at the next login. Until then, wrap serial commands in
`sg dialout -c "…"`.

```bash
# StandardFirmata (57600 baud)
arduino-cli compile --upload -p /dev/ttyACM0 --fqbn arduino:renesas_uno:unor4wifi \
  --library Firmata Firmata/examples/StandardFirmata
tools/firmata_probe.py /dev/ttyACM0 57600

# ConfigurableFirmata (115200 baud)
arduino-cli compile --upload -p /dev/ttyACM0 --fqbn arduino:renesas_uno:unor4wifi \
  --library ConfigurableFirmata ConfigurableFirmata/examples/ConfigurableFirmata
tools/firmata_probe.py /dev/ttyACM0 115200
```

[`tools/firmata_probe.py`](tools/firmata_probe.py) is a minimal Firmata host that
needs only `pyserial`. It checks protocol version, firmware name, capabilities,
analog mapping, an LED blink on pin 13, analog reporting on A0, and PWM mode on
pin 3. Results on 2026-10-09:

| Check | StandardFirmata 2.5 | ConfigurableFirmata 3.4 |
|---|---|---|
| Handshake through the ESP32-S3 bridge | Pass | Pass |
| PWM pins before the fix | 0–13, 18, 19 | 0–13, 18, 19 |
| PWM pins after the fix | 3, 5, 6, 9, 10, 11 | 3, 5, 6, 9, 10, 11 |
| Analog pins / mapping | 14–19 → A0–A5 | 14–19 → A0–A5 |
| Pin 13 output, A0 reporting | Pass | Pass |
| PWM on pin 3 | Mode PWM, value 128 | Mode PWM. ConfigurableFirmata never stores PWM values in its pin state, so the value reads back as 0 on any board. |

Not yet verified: a real vvvv session, and PWM measured on a scope or with an LED
on the `~` pins.

## LED Matrix Support

The UNO R4 WiFi has a 12 × 8 red LED matrix, driven by the RA4M1 through the
`Arduino_LED_Matrix` library that ships with the board package. Standard Firmata
has no message for it, so
[`sketches/ConfigurableFirmataR4/LedMatrixFirmata.h`](sketches/ConfigurableFirmataR4/LedMatrixFirmata.h)
adds one as a ConfigurableFirmata feature. It uses sysex command `0x01`, which
the Firmata protocol reserves for user-defined commands.

| Message | Bytes |
|---|---|
| Set frame | `F0 01 00 <14 bytes> F7` |
| Query frame | `F0 01 01 F7` → reply `F0 01 02 <cols=12> <rows=8> <14 bytes> F7` |
| Set one LED | `F0 01 03 <index 0–95> <0 or 1> F7` |

- **LED index** = row × 12 + column. Row 0, column 0 is the top-left LED, using
  the `Arduino_LED_Matrix` convention.
- **Frame encoding:** Firmata data bytes carry 7 bits, so the 96 LEDs are packed
  7 per byte. LED *i* is bit *i* % 7 of byte *i* / 7, giving 14 bytes.
- **Presence detection:** a host sends the query. A reply means the firmware has
  a matrix and reports its size; no reply means it has none.
- **On/off only.** The library has no per-LED brightness.

The sketch is the stock ConfigurableFirmata example with the feature added. It
compiles the feature only for `ARDUINO_UNOR4_WIFI`, so the same sketch still
builds for the R4 Minima and other boards. The matrix refresh timer starts only
on the first matrix command. It can't take a PWM timer: the board package
reserves the timer channels of exactly the `~` pins (3, 5, 6, 9, 10, 11) for PWM
at boot, and the matrix library only takes channels that are still free.

From any host, for example Python with pyserial:

```python
import serial

def pack(leds):  # 96 booleans -> 14 bytes
    out = bytearray(14)
    for i, on in enumerate(leds):
        if on:
            out[i // 7] |= 1 << (i % 7)
    return bytes(out)

with serial.Serial("/dev/ttyACM0", 115200) as s:
    border = [r in (0, 7) or c in (0, 11) for r in range(8) for c in range(12)]
    s.write(b"\xF0\x01\x00" + pack(border) + b"\xF7")   # draw a border
    s.write(bytes([0xF0, 0x01, 0x03, 5 * 12 + 6, 1, 0xF7]))  # LED at row 5, col 6 on
```

`tools/firmata_probe.py` checks the feature automatically. It draws a
checkerboard, switches one LED off, clears the matrix, and verifies each step by
reading the frame back.

## Firmata Test GUI

[firmata_test](https://github.com/firmata/firmata_test) is the classic interactive
tester. It shows one row per pin, built from the board's capability response,
with a mode dropdown, output toggles, PWM sliders and live input values. It lives
in `tools/firmata_test` as a submodule. Upstream doesn't work with this board on
current Linux, so
[`patches/firmata_test-linux-unor4.patch`](patches/firmata_test-linux-unor4.patch)
makes four changes:

- **wxWidgets 3.2:** `wxMenuItem::GetLabel()` becomes `GetItemLabelText()`.
- **Baud menu:** upstream hardcodes 57600. Choose 57600 (StandardFirmata) or
  115200 (ConfigurableFirmata). Changing the rate while connected reopens the port.
- **DTR/RTS raised on open:** upstream's Linux `Serial::Open()` lowers DTR and RTS
  so classic Arduinos don't reset. The UNO R4 WiFi's ESP32-S3 USB bridge sends
  **nothing** to the host while DTR is low. Tested: 0 bytes back with DTR off, a
  full firmware report with DTR on. Without this change the window stays empty
  (status shows `Tx:3 Rx:0`). Boards that do reset on DTR still work, because they
  send the firmware report after booting and firmata_test waits for it.
- **LED Matrix window:** after the firmware report, firmata_test also sends the
  [matrix query](#led-matrix-support). If the board answers, a separate
  **LED Matrix** window opens with a 12 × 8 grid of toggle buttons plus
  **Clear**, **All on** and **Invert**. Each click is sent to the board at once
  (single-LED message for a click, whole frame for the buttons). Closing the
  window only hides it; **View → LED Matrix** brings it back. Boards without the
  feature never answer, so for them nothing changes.

Build and run:

```bash
sudo apt-get install -y libwxgtk3.2-dev build-essential
git submodule update --init Arduino/tools/firmata_test   # from the repo root
cd Arduino/tools/firmata_test
git apply ../../patches/firmata_test-linux-unor4.patch
make WXCONFIG=wx-config
./firmata_test
```

Pick the baud rate under **Baud** first, then the port (`/dev/ttyACM0`) under
**Port**. The pin rows appear once the firmware name arrives. Verified on
2026-10-09 with the UNO R4 WiFi running ConfigurableFirmata 3.4 at 115200 baud.

### Testing Guide

Connect at **115200 / `/dev/ttyACM0`** and work through the steps in order. If
a step fails, stop there.

**1. Without wiring anything**

- **Status bar:** shows `ConfigurableFirmata-3.4`, with `Tx:` and `Rx:` both
  counting up. If `Rx` stays at 0, the board isn't answering (see the DTR note
  above).
- **Pin list:** starts at pin 2 and runs to 19. The PWM option appears only on
  pins **3, 5, 6, 9, 10, 11**, which confirms the [PWM fix](#solution).
- **LED on pin 13:** set pin 13 to **Output** and click its toggle. The orange
  **L** LED next to the USB port switches on and off.
- **Analog inputs:** pins 14–19 (A0–A5) show changing `A0: …` values. Pins with
  nothing connected drift randomly, and touching one makes it jump. That's normal.
- **LED matrix** (needs `sketches/ConfigurableFirmataR4`): the **LED Matrix**
  window opens by itself. Click the top-left cell and the top-left LED of the
  matrix lights up (USB connector on the left). **All on**, **Invert** and
  **Clear** change the whole matrix at once.

**2. With one jumper wire (male-to-male)**

- **Pullup input:** set pin 2 to **Pullup**; it reads **High**. Connect pin 2 to
  **GND** and it switches to **Low**. Unplug the wire and it goes back to High.
- **Output to input loopback:** set pin 7 to **Output** and pin 8 to **Input**,
  then wire 7 to 8. Toggling pin 7 flips pin 8 between High and Low. This checks
  digital output and input in one go.
- **Analog readings:** on pin 14 (A0), wire A0 to **GND** for about 0, to
  **3.3V** for about 675, and to **5V** for about 1023. Values within a few counts
  are fine.

**3. With an LED or a servo**

- **PWM:** connect pin 9 → 220 Ω resistor → LED (long leg) → LED short leg →
  GND. Set pin 9 to **PWM** and drag the slider; the LED fades smoothly. Repeat
  on 3, 5, 6, 10 and 11.
- **Servo:** connect the servo's signal wire to pin 9, power to 5V and ground to
  GND. Set pin 9 to **Servo**; the slider moves it from 0 to 180°.

Don't wire a PWM pin straight to A0 to check PWM. A0 samples the fast on/off
signal and shows random-looking values unless you add a resistor-capacitor
filter. Use the LED instead.

**What firmata_test can't test**

firmata_test only knows input, output, analog, PWM, servo and pullup.
ConfigurableFirmata's I2C, SPI, DHT sensor and frequency-counting features don't
appear in its menus, which doesn't mean they're broken. Testing those needs a
script or a host such as vvvv. The board's WiFi isn't reachable through Firmata
at all.

If everything in steps 1 and 2 behaves as described, digital I/O, pullups,
analog input and the serial link through the ESP32 bridge are all working.
Step 3 confirms PWM and servo on real hardware.

## Work Log

2026-10-09, UNO R4 WiFi on `/dev/ttyACM0`:

1. **Research.** Checked the upstream versions and R4 issues, PRs and forum
   threads (see [Upstream Versions](#upstream-versions) and
   [The UNO R4 Problem](#the-uno-r4-problem)).
2. **Machine setup.** Added the user to `dialout`, installed `arduino-cli` 1.5.1
   in `~/.local/bin`, the `arduino:renesas_uno` 1.6.0 core, the
   `DHT sensor library` and `Servo` libraries, and `libwxgtk3.2-dev` for
   firmata_test.
3. **First hardware test.** Flashed ConfigurableFirmata and StandardFirmata. Both
   handshake and run I/O through the ESP32-S3 bridge, which ruled out the bridge
   theory. Both over-reported PWM pins.
4. **PWM fix.** Restricted PWM to pins 3, 5, 6, 9, 10 and 11 in `Firmata/Boards.h`
   and in the ConfigurableFirmata submodule
   (`patches/ConfigurableFirmata-unor4-pwm-pins.patch`). Retested both libraries
   with `tools/firmata_probe.py`; both pass.
5. **GUI.** Added `tools/firmata_test` as a submodule and fixed it for wxWidgets
   3.2, configurable baud and DTR handling
   (`patches/firmata_test-linux-unor4.patch`). Confirmed working interactively.
6. **Reproducibility.** Switched the ConfigurableFirmata submodule to HTTPS,
   pinned all versions, added the [Quick Start](#quick-start-from-a-fresh-clone),
   and ran it end to end from a clean clone with SSH disabled; the probe passed.
7. **LED matrix.** Added the `LedMatrixFirmata` feature and the
   `sketches/ConfigurableFirmataR4` sketch, a matrix check in
   `tools/firmata_probe.py`, and the LED Matrix window in firmata_test (same
   patch). The probe's checkerboard, single-LED and clear readbacks pass.

The board was left running `sketches/ConfigurableFirmataR4` (ConfigurableFirmata
3.4 with the PWM patch and the LED matrix feature) at 115200 baud.

## Sources

- [firmata/arduino releases](https://github.com/firmata/arduino/releases)
- [firmata/arduino PR #509: Arduino Uno R4 support](https://github.com/firmata/arduino/pull/509)
- [firmata/arduino PR #520: UNO R4 WiFi PWM/ADC pin detection](https://github.com/firmata/arduino/pull/520)
- [firmata/ConfigurableFirmata tags](https://github.com/firmata/ConfigurableFirmata/tags)
- [ConfigurableFirmata issue #167: UNO R4 WiFi IS_PIN_PWM conflict](https://github.com/firmata/ConfigurableFirmata/issues/167)
- [ConfigurableFirmata PR #168: Fix build for UnoR4 Wifi/Minima](https://github.com/firmata/ConfigurableFirmata/pull/168)
- [ConfigurableFirmata PR #182: Fix interrupts on the Uno R4](https://github.com/firmata/ConfigurableFirmata/pull/182)
- [Arduino Forum: Firmata via USB on UNO R4 WiFi](https://forum.arduino.cc/t/firmata-via-usb-on-uno-r4-wifi/1383790)
- [Arduino Forum: Adding Arduino R4 WiFi to Firmata Boards.h](https://forum.arduino.cc/t/adding-arduino-r4-wifi-to-firmata-board-h-file/1247689)
- [firmata/firmata_test](https://github.com/firmata/firmata_test)
