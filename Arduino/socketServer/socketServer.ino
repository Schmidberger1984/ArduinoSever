#include <WiFi.h>
#include <Arduino_JSON.h>

char json[40];
char data[40];
int count=0;
int size=0;
const char* ssid = "PBS-EE5C60";
const char* password =  "sw5610op";

 
WiFiServer wifiServer(5000);
 
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
         if (c=='}'){
                      JSONVar myObject = JSON.parse(json);
                      if (myObject.hasOwnProperty("Pin")){
                        int pin=(int) myObject["Pin"];
                        bool value=(bool) myObject["setValue"];
                        digitalWrite(pin,value);
                        for (int i=0;i<=count;i++){
                                  Serial.print(json[i]);
                                  client.write(json[i]);
                              }
                        
                      }
                      if (myObject.hasOwnProperty("APin")){
                        int value=analogRead((int) myObject["APin"]);
                        Serial.println("{\"ID\":1,\"APin\":2}");
                        String test="{\"ID\":1,\"APin\":2,\"Value\":"+String(value)+"}";
                        size=test.length();
                        test.toCharArray(data,40);
                        for (int i=0;i<size;i++){
                                  Serial.print(data[i]);
                                  client.write(data[i]);
                              }
                      }
              client.stop();
             break; 
          };
        count++;
        }   
    }  
    Serial.println();
    Serial.println("--------------");
    count=0;
    //delete Array String
    for (int i=0;i<sizeof(json);i++){
      json[i]=0;
    }
  }
}
