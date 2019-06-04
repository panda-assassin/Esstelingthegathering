#include <Arduino.h>

const int GREEN1 = 5;
const int GREEN2 = 18;
const int YELLOW1 = 19;
const int YELLOW2 = 16;
const int RED1 = 17;
const int RED2 = 21;


void setup() {
  pinMode(GREEN1, OUTPUT);
  pinMode(GREEN2, OUTPUT);
  pinMode(YELLOW1, OUTPUT);
  pinMode(YELLOW2, OUTPUT);
  pinMode(RED1, OUTPUT);
  pinMode(RED2, OUTPUT);
  Serial.begin(115200);


}

void loop() {

    int read = analogRead(A0);
    if ( read > 682) {
      digitalWrite(GREEN1, HIGH);
      if ( read > 1365) {
        digitalWrite(GREEN2, HIGH);
        if ( read > 2047) {
          digitalWrite(YELLOW1, HIGH);
          if ( read > 2730) {
            digitalWrite(YELLOW2, HIGH);
            if ( read > 3412) {
              digitalWrite(RED1, HIGH);
              if ( read > 4000) {
                digitalWrite(RED2, HIGH);
              }
            }
          }
        }
      }
    }
    if ( read < 4000) {
      digitalWrite(RED2, LOW);
      if ( read < 3412) {
        digitalWrite(RED1, LOW);
        if ( read < 2730) {
          digitalWrite(YELLOW2, LOW);
          if ( read < 2047) {
            digitalWrite(YELLOW1, LOW);
            if ( read < 1365) {
              digitalWrite(GREEN2, LOW);
              if ( read < 682) {
                digitalWrite(GREEN1, LOW);
              }
            }
          }
        }
      }
    }
    delay(100);
}
