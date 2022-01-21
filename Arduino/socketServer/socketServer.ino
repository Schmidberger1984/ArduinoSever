#include <WiFi.h>
#include <ArduinoJson.h>

char json[100];
int count=0;
const char* ssid = "PBS-EE5C60";
const char* password =  "sw5610op";
 
WiFiServer wifiServer(90);
 
void setup() {
  StaticJsonDocument<200> doc;
  Serial.begin(115200);
 
  delay(1000);
 
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
 
  Serial.println("Connected to the WiFi network");
  Serial.println(WiFi.localIP());
 
  wifiServer.begin();
}
 
void loop() {
 
  WiFiClient client = wifiServer.available();
 
  if (client) {
 
    while (client.connected()) {
 
      while (client.available()>0) {
        char c = client.read();
        json[count]=c;
        client.write(c);
        Serial.print(c);
        if (c=='e') Serial.println("es geht");
      }
 
      delay(10);
    }
 
    client.stop();
  }
}
