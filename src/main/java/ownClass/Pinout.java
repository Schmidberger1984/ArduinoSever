package ownClass;

public class Pinout {
    String type;
    String pin;
    String ArduinoID;

    public Pinout(String type, String pin, String ArduinoID) {
        this.type = type;
        this.pin = pin;
        this.ArduinoID = ArduinoID;
    }

    @Override
    public String toString() {
        return "Pinout{" +
                "type='" + type + '\'' +
                ", pin='" + pin + '\'' +
                ", ArduinoID='" + ArduinoID + '\'' +
                '}';
    }
}
