/*
  LedMatrixFirmata.h - ConfigurableFirmata feature for the 12x8 LED matrix
  of the Arduino UNO R4 WiFi.

  Uses the user-defined sysex command 0x01. LED index = row * 12 + column,
  row 0 / column 0 is the top-left LED (the Arduino_LED_Matrix convention).

    Set frame:   F0 01 00 <14 bytes> F7
    Query frame: F0 01 01 F7
      Reply:     F0 01 02 <cols=12> <rows=8> <14 bytes> F7
    Set one LED: F0 01 03 <index 0..95> <0|1> F7

  A frame is 96 bits packed 7 per byte: LED i is bit (i % 7) of byte (i / 7).
  The query doubles as presence detection: no reply means no matrix.
*/

#ifndef LedMatrixFirmata_h
#define LedMatrixFirmata_h

#include <ConfigurableFirmata.h>
#include <FirmataFeature.h>
#include <Arduino_LED_Matrix.h>

#define LED_MATRIX_DATA         0x01 // user-defined sysex command
#define LED_MATRIX_SET_FRAME    0x00
#define LED_MATRIX_QUERY_FRAME  0x01
#define LED_MATRIX_FRAME_REPORT 0x02
#define LED_MATRIX_SET_PIXEL    0x03

#define LED_MATRIX_COLS         12
#define LED_MATRIX_ROWS         8
#define LED_MATRIX_LEDS         (LED_MATRIX_COLS * LED_MATRIX_ROWS)
#define LED_MATRIX_PACKED_BYTES ((LED_MATRIX_LEDS + 6) / 7)

class LedMatrixFirmata : public FirmataFeature
{
  public:
    void handleCapability(byte pin) override {}

    boolean handlePinMode(byte pin, int mode) override
    {
      return false;
    }

    boolean handleSysex(byte command, byte argc, byte *argv) override
    {
      if (command != LED_MATRIX_DATA || argc < 1) {
        return false;
      }
      switch (argv[0]) {
        case LED_MATRIX_SET_FRAME:
          if (argc < 1 + LED_MATRIX_PACKED_BYTES) {
            return false;
          }
          for (int i = 0; i < LED_MATRIX_LEDS; i++) {
            setBit(i, argv[1 + i / 7] & (1 << (i % 7)));
          }
          show();
          return true;
        case LED_MATRIX_SET_PIXEL:
          if (argc < 3 || argv[1] >= LED_MATRIX_LEDS) {
            return false;
          }
          setBit(argv[1], argv[2]);
          show();
          return true;
        case LED_MATRIX_QUERY_FRAME:
          reportFrame();
          return true;
      }
      return false;
    }

    void reset() override
    {
      frame[0] = frame[1] = frame[2] = 0;
      if (started) {
        show();
      }
    }

  private:
    ArduinoLEDMatrix matrix;
    uint32_t frame[3] = {0, 0, 0};
    bool started = false;

    // Arduino_LED_Matrix keeps LED 0 in the most significant bit of frame[0]
    void setBit(int i, bool on)
    {
      uint32_t mask = 1UL << (31 - (i % 32));
      if (on) {
        frame[i / 32] |= mask;
      } else {
        frame[i / 32] &= ~mask;
      }
    }

    bool getBit(int i)
    {
      return frame[i / 32] & (1UL << (31 - (i % 32)));
    }

    // Start the matrix refresh timer only once the matrix is actually used
    void show()
    {
      if (!started) {
        matrix.begin();
        started = true;
      }
      matrix.loadFrame(frame);
    }

    void reportFrame()
    {
      Firmata.startSysex();
      Firmata.write(LED_MATRIX_DATA);
      Firmata.write(LED_MATRIX_FRAME_REPORT);
      Firmata.write(LED_MATRIX_COLS);
      Firmata.write(LED_MATRIX_ROWS);
      for (int b = 0; b < LED_MATRIX_PACKED_BYTES; b++) {
        byte packed = 0;
        for (int bit = 0; bit < 7 && b * 7 + bit < LED_MATRIX_LEDS; bit++) {
          if (getBit(b * 7 + bit)) {
            packed |= 1 << bit;
          }
        }
        Firmata.write(packed);
      }
      Firmata.endSysex();
    }
};

#endif
