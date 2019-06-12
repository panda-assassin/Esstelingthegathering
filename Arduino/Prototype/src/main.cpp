/*---------------------------------------------------------------------------------------------------

Author: Pascal Holthuijsen
file: main.cpp
Proftaak prototype group B6
Prototype for the rollercoaster Microphone module(no microphone included)
due too problems microphone doesnt work using a potentiometer instead

checks the level of the potentiometer and checks the highest number
over 5 second may varry voor the lenght of the ride sends the highestRead
as a number inside a json object tothe mqtt broker on the topic B6-project-led-data
wich can be read using the essteling the gathering app



----------------------------------------------------------------------------------------------------*/

#include <Arduino.h>
#include <ArduinoJson.h>
#include <WiFi.h>
#include <Ticker.h>
#include <PubSubClient.h>

//wifi connection
const char *ssid = "myphone";
const char *password = "ichbinhenk";
WiFiClient wifiClient;

//MQTT data
const char* mqtt_broker   = "51.254.217.43";
const char* mqtt_topic    = "B6-project-led-data";
const char* mqtt_username = "emon";
const char* mqtt_password = "uw2ELjAKrEUwqgLT";
PubSubClient mqttClient = PubSubClient(WiFi.localIP(), 0, wifiClient);

//LED pins
const int GREEN1 = 5;
const int GREEN2 = 18;
const int YELLOW1 = 19;
const int YELLOW2 = 16;
const int RED1 = 17;
const int RED2 = 21;

//data for sending information
Ticker ticker;
int highestRead = 0;
int whythefckyounowork = 0;

void mqtt_connect() {
  mqttClient.setClient(wifiClient);
  mqttClient.setServer(mqtt_broker, 1883);

  //connect with a unique id
  String clientId = "TIB6-";
  clientId += String(random(0xffff), HEX);
  clientId += '-';
  clientId += String((uint32_t)ESP.getEfuseMac(), HEX);
  if(mqttClient.connect( clientId.c_str(), mqtt_username, mqtt_password )){

   // Subscribe to topic
   mqttClient.subscribe(mqtt_topic);

   // Setup callback
 //   mqttClient.setCallback(mqtt_callback);
 //   Serial.printf("%s: Connected to %s:%d\n", __FUNCTION__, mqtt_broker, 1883);
 // } else {
 //   Serial.printf("%s: Connection ERROR (%s:%d)\n", __FUNCTION__, mqtt_broker, 1883);
 //   delay(2000);
 // }

  }
}

void mqtt_pubish(int dataInteger) {
  String justsomething = dataInteger = "";
    mqttClient.publish(mqtt_topic, justsomething + "");
}

void setup() {
  pinMode(GREEN1, OUTPUT);
  pinMode(GREEN2, OUTPUT);
  pinMode(YELLOW1, OUTPUT);
  pinMode(YELLOW2, OUTPUT);
  pinMode(RED1, OUTPUT);
  pinMode(RED2, OUTPUT);
  Serial.begin(115200);
  //ticker.attach(5.0, senddata())
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("wifi connection connected to the wifi");
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
      if (!mqttClient.connected()) {
          mqtt_connect();
      }else {
        mqttClient.loop();
      }
  }

    int read = analogRead(A0);
    //Serial.println(read);
    read = random(0, 682) * 6;// remove this line
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
    if (read > highestRead) {
      highestRead = read;
    }
    if (whythefckyounowork > 50 ) {
      highestRead = 0;
    }


    mqtt_pubish(160);
    whythefckyounowork += 1;
    delay(100);
}
