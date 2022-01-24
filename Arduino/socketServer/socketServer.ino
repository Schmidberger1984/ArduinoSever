#include <WiFi.h>
#include <Arduino_JSON.h>


char json[40];
//char  json[]="{\"id\":5,\"port\":0,\"Pin\":7,\"setValue\":true}";
int count=0;
int run1=0;
const char* ssid = "PBS-EE5C60";
const char* password =  "sw5610op";

 
WiFiServer wifiServer(90);
 
void setup() {
  pinMode(2,OUTPUT);
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
        client.write(c);
        json[count]=c;
        Serial.print(c);
        count++;
        }
    }
    JSONVar myObject = JSON.parse(json);
    int pin=(int) myObject["Pin"];
    bool value=(bool) myObject["setValue"];
    Serial.println(pin);
    Serial.println(value);
    digitalWrite(pin,value);
    client.stop();
    count=0;
  }
}
