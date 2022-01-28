package ownClass;

import Database.Statements;
import com.google.gson.Gson;

public class ArduinoGet extends Arduino {
    int APin =0;
    int Value=0;

    public ArduinoGet(int ID, int APin) {
        super(ID);
        this.APin = APin;
    }
    public ArduinoGet(int ID, int APin, int Value) {
        super(ID);
        this.APin = APin;
        this.Value=Value;
    }

    public static String Get(String setval,Arduino setting){
        SocketClient ardunio=new SocketClient(setting.ipAdd,setting.Port);
        String temp =ardunio.sendData(setval);
        Gson gson=new Gson();
        ArduinoGet data=gson.fromJson(temp,ArduinoGet.class);
        Convert convert = new Convert( data.Value);
        Statements statement= new Statements();
        try {
            statement.insertWeatherData(convert.toGrad(), convert.toHumidity(), setting.ID);
        }catch ( Exception e) {
            System.out.println(e);
        }
        if (!setval.contains(temp.substring(0,setval.length()-1))) return "Ardunio has not Json received";
        return "Ardunio has Json received";
    }
}
