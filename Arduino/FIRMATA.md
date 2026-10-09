# Firmata on the Arduino UNO R4 WiFi

Status as of 2026-10-09: what upstream ships, what actually broke on the UNO R4,
and where the copies in this directory stand.

## Table of Contents

- [Hardware](#hardware)
- [Upstream Versions](#upstream-versions)
- [The UNO R4 Problem](#the-uno-r4-problem)
- [Is the ESP32-S3 Serial Bridge to Blame?](#is-the-esp32-s3-serial-bridge-to-blame)
- [Local Copies in This Directory](#local-copies-in-this-directory)
- [Next Steps](#next-steps)
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

One loose end: a forum user reported in 2025-05 that ConfigurableFirmata 3.3.0
worked on the Minima but **not** on the WiFi. The thread never resolved why. The
bridge is the obvious hardware difference, but a WiFi-specific pin-definition
problem would explain it just as well, and both #168 and #182 have landed since.
Only a test on real hardware can settle it.

## Local Copies in This Directory

| Path | Version | R4 state |
|---|---|---|
| `Firmata/` | 2.5.9 plus development-branch R4 support | `Firmata/Boards.h` (R4 section around line 459) contains the PR #520 fix by hand, marked with "25.03." comments. `Boards_h.old` / `Boards_h.new` are earlier edit snapshots. |
| `ConfigurableFirmata/` | Git submodule on upstream head, 3.4.0 | Includes PR #155, #168 and #182, so it is as current as upstream. |
| `Firmata_README.md` | Instructions in German | Describes a Firmata **2.5.7** with an extended `Boards.h` that works on the UNO R3, R4 Minima and R4 WiFi with vvvv beta and gamma as host. |

On paper, both libraries are as fixed as anything upstream offers. **Neither has
been verified on the attached board yet.**

## Next Steps

1. Install `arduino-cli` and the Renesas core:

   ```bash
   arduino-cli core update-index
   arduino-cli core install arduino:renesas_uno
   ```

2. Flash ConfigurableFirmata from the submodule:

   ```bash
   arduino-cli compile --upload -p /dev/ttyACM0 \
     --fqbn arduino:renesas_uno:unor4wifi \
     --library ConfigurableFirmata ConfigurableFirmata/examples/ConfigurableFirmata
   ```

   Check the baud rate first. `Firmata.begin(115200)` in the sketch must match the host.
3. Connect with a Firmata host (vvvv, or `pyfirmata2` / `telemetrix` for a quick
   check) and confirm the handshake and the reported pin capabilities.
4. Repeat with `Firmata/examples/StandardFirmata` (`--library Firmata`).
5. If either fails only on the WiFi and not on the Minima, look at the ESP32
   bridge. Update its firmware with `unor4wifi-updater` and retest.

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
