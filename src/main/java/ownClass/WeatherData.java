package ownClass;

public class WeatherData {
    public String temperature;
    public String humidity;
    public String date;
    public String time;
    public int ID;

    public WeatherData(String temperature, String humidity, String date, String time, String ID) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
        this.time = time;
        this.ID =Integer.valueOf(ID);;
        ;
    }
}
