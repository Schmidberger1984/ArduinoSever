package ownClass;

import Database.MySQL;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        MySQL.connect();
        ArrayList<String> date=MySQL.select("SELECT * FROM weatherdata","Date");
        ArrayList<String> time=MySQL.select("SELECT * FROM weatherdata","Time");
        ArrayList<String> temp=MySQL.select("SELECT * FROM weatherdata","Temperature");
        ArrayList<String> humidity=MySQL.select("SELECT * FROM weatherdata","Humidity");
        WeatherData current =new WeatherData(temp.get(temp.size()-1),humidity.get(humidity.size()-1),date.get(date.size()-1),time.get(time.size()-1));
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
