# CLAUDE.md

Firmata on the Arduino UNO R4 WiFi (also R3 / R4 Minima), with vvvv or the
`firmata_test` GUI as host. Start with `README.md`, then `FIRMATA.md`.

## Layout

- `Firmata/` is StandardFirmata 2.5.9 with the fixed `Boards.h`. It is committed
  with its fix and needs no patch.
- `ConfigurableFirmata/` and `tools/firmata_test/` are upstream submodules. Never
  commit inside them. Local changes live as patches in `patches/` and are applied
  with `git apply` after cloning (see the Quick Start in `FIRMATA.md`).
- `sketches/ConfigurableFirmataR4/` is the stock example plus the LED matrix
  feature (`LedMatrixFirmata.h`).
- `tools/firmata_probe.py` is the automated check for a flashed board and must
  end with `RESULT: PASS`.
- `Copilot.AI/` is abandoned history. Leave it alone.

## Conventions

- If you change a submodule fix, regenerate the matching file in `patches/`.
- Only one program can hold `/dev/ttyACM0`. Close the GUI before uploading.
- The UNO R4 PWM pins are 3, 5, 6, 9, 10 and 11. Do not report more.
- Record notable findings in the Work Log in `FIRMATA.md`.
- Commit directly on `main`. This is a single-contributor repository.
