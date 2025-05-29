// https://github.com/copilot/share/826b00b4-40e4-8453-a901-a241c447694f

#include <ArduinoGraphics.h>
#include <Arduino_LED_Matrix.h>

ArduinoLEDMatrix matrix;
#ifndef graphics
#define graphics matrix
#endif

void setup() {
  matrix.begin();
}

void loop() {
  // Draw a moving diagonal line
  graphics.beginDraw();
  graphics.stroke(0xFF); // white
  graphics.fill(0x00);   // black
  graphics.clear();
  for (int i = 0; i < 8; i++) {
    graphics.clear();
    graphics.line(0, 0, 11, i);
    graphics.endDraw();
    delay(100);
    graphics.beginDraw();
  }
  graphics.endDraw();

  // Scroll some text
  graphics.beginDraw();
  graphics.clear();
  graphics.textFont(Font_5x7);
  // graphics.cursor(0, 0);
  // graphics.print("UNO R4 WiFi!");
  graphics.text("R4", 1, 1); // Start at x=0, y=3
  graphics.endDraw();
  delay(1000);
}