package ownClass;

import Database.Statements;
import com.google.gson.Gson;

public class Threadnew extends Thread{
    public void run(){
        SocketClient ardunio=new SocketClient("10.0.0.12",5000);
        while(true){
        String temp =ardunio.sendData("{\"ID\":1,\"APin\":34}");
        Gson gson=new Gson();
        ArduinoGet data=gson.fromJson(temp,ArduinoGet.class);
        Convert convert = new Convert( data.Value);
        Statements statement= new Statements();
        try {
            statement.insertWeatherData(convert.toGrad(), convert.toHumidity(), 3);
            Thread.sleep(1000);
        }catch ( Exception e) {
            System.out.println(e);
        }
        }
    }
}
