package ownClass;

public class TriggerSettings {
    String ArduinoID;
    double onTemp;
    double offTemp;
    double onHumid;
    double offHumid;
    boolean outside;
    boolean enable;

    public TriggerSettings(String arduinoID, double onTemp, double offTemp, double onHumid, double offHumid, boolean outside, boolean enable) {
        ArduinoID = arduinoID;
        this.onTemp = onTemp;
        this.offTemp = offTemp;
        this.onHumid = onHumid;
        this.offHumid = offHumid;
        this.outside = outside;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "TriggerSettings{" +
                "ArduinoID='" + ArduinoID + '\'' +
                ", onTemp=" + onTemp +
                ", offTemp=" + offTemp +
                ", onHumid=" + onHumid +
                ", offHumid=" + offHumid +
                ", outside=" + outside +
                ", enable=" + enable +
                '}';
    }
}
