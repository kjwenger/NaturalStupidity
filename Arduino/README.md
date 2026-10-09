# Arduino
Firmata on the Arduino UNO R4 WiFi (and R3 / R4 Minima), with vvvv as the host.

## Table of Contents

- [Layout](#layout)
- [Firmata on the UNO R4 WiFi](#firmata-on-the-uno-r4-wifi)

## Layout

| Path | Contents |
|---|---|
| `Firmata/` | Standard Firmata 2.5.9 with a hand-patched `Boards.h` for the UNO R4 |
| `ConfigurableFirmata/` | Git submodule of [firmata/ConfigurableFirmata](https://github.com/firmata/ConfigurableFirmata) |
| `Firmata_README.md` | Installation instructions (German) for the patched Firmata with vvvv |
| `FIRMATA.md` | UNO R4 compatibility analysis: upstream versions, root cause, fixes, next steps |
| `Copilot.AI/` | Earlier GitHub Copilot session notes. Abandoned and kept only as history. |

## [Firmata on the UNO R4 WiFi](FIRMATA.md)

In short: neither Firmata library's latest release fully supports the UNO R4.
The failures come from a `Boards.h` macro clash with the Renesas core
(`IS_PIN_PWM` / `IS_PIN_ANALOG`), not from the ESP32-S3 USB-serial bridge. Both
local copies contain the upstream fixes but haven't been tested on hardware yet.
