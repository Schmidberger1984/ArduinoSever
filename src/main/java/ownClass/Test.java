package ownClass;

import Database.MySQL;
import Database.Statements;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        SocketClient test2 = new SocketClient("10.0.0.12");
        test2.sendData("");
        MySQL.connect();
        ArrayList<String> date=MySQL.select("SELECT * FROM weatherdata","Date");
        ArrayList<String> time=MySQL.select("SELECT * FROM weatherdata","Time");
        ArrayList<String> temp=MySQL.select("SELECT * FROM weatherdata","Temperature");
        ArrayList<String> humidity=MySQL.select("SELECT * FROM weatherdata","Humidity");
        int temperature=2;
        int humidity2=3;
        int result=MySQL.insert("INSERT INTO weatherdata (Temperature, Humidity) VALUES ('"+temperature+"','"+humidity2+"')");
        System.out.println(result);
        WeatherData current =new WeatherData(temp.get(temp.size()-1),humidity.get(humidity.size()-1),date.get(date.size()-1),time.get(time.size()-1));
        Statements statement2=new Statements();
        LocalDate searchDate= LocalDate.now();
        statement2.getDayWeather(String.valueOf(searchDate));
        Gson gson=new Gson();

        System.out.println(temp);
        ArrayList<WeatherData> test =new ArrayList<WeatherData>();
        test.add(current);
        test.add(current);
        String ad=gson.toJson(test);
        System.out.println(ad);
        LocalDate test1= LocalDate.now();
        System.out.println(test1);
        String ha=String.valueOf(4.5);
        System.out.println(ha);
    }
}
