# Firmata on the Arduino UNO R4 WiFi

Status as of 2026-10-09: what upstream ships, what actually broke on the UNO R4,
and where the copies in this directory stand.

## Table of Contents

- [Hardware](#hardware)
- [Upstream Versions](#upstream-versions)
- [The UNO R4 Problem](#the-uno-r4-problem)
- [Is the ESP32-S3 Serial Bridge to Blame?](#is-the-esp32-s3-serial-bridge-to-blame)
- [The Remaining Bug: PWM Over-Reporting](#the-remaining-bug-pwm-over-reporting)
- [Solution](#solution)
- [Local Copies in This Directory](#local-copies-in-this-directory)
- [Verification](#verification)
- [Sources](#sources)

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

Setup: `arduino-cli` 1.5.1 in `~/.local/bin` and the `arduino:renesas_uno` 1.6.0
core. ConfigurableFirmata also needs the `DHT sensor library` and `Servo`
libraries. The user must be in the `dialout` group to open `/dev/ttyACM0`.
Membership takes effect at the next login. Until then, wrap serial commands in
`sg dialout -c "…"`.

```bash
# One-time setup
curl -fsSL https://raw.githubusercontent.com/arduino/arduino-cli/master/install.sh \
  | BINDIR=$HOME/.local/bin sh
sudo usermod -aG dialout "$USER"
arduino-cli core update-index
arduino-cli core install arduino:renesas_uno
arduino-cli lib install "DHT sensor library" Servo

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
