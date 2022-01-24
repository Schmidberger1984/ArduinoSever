package ownClass;

public class ArduinoGet extends Ardunio {
    int APin =0;

    public ArduinoGet(int ID, int APin) {
        super(ID);
        this.APin = APin;
    }

    public static String Get(String setval){
        SocketClient ardunio=new SocketClient("10.0.0.12");
        return ardunio.sendData(setval);
    }
}
