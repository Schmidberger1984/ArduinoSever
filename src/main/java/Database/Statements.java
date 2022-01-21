package Database;

import com.google.gson.Gson;
import ownClass.WeatherData;

import java.time.LocalDate;
import java.util.ArrayList;



public class Statements {

    public String getcurrentWeather(){
        MySQL.connect();
        ArrayList date=MySQL.select("SELECT * FROM weatherdata","Date");
        ArrayList time=MySQL.select("SELECT * FROM weatherdata","Time");
        ArrayList temp=MySQL.select("SELECT * FROM weatherdata","Temperature");
        ArrayList humidity=MySQL.select("SELECT * FROM weatherdata","Humidity");
        Gson gson=new Gson();
        ArrayList<WeatherData> arraydata=new ArrayList<>();
        arraydata.add(new WeatherData(LastEntry(temp),LastEntry(humidity),LastEntry(date),LastEntry(time)));
        String sendData=gson.toJson(arraydata);
        MySQL.close();
        return sendData;
    }

    private String LastEntry(ArrayList data){
        return (String) data.get(data.size()-1);
    }

    public String getDayWeather(String inputdate){
        LocalDate searchDate= LocalDate.now();
        if (inputdate!=null) searchDate= LocalDate.parse(inputdate);
        MySQL.connect();
        ArrayList date=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"'","Date");
        ArrayList time=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"'","Time");
        ArrayList temp=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"'","Temperature");
        ArrayList humidity=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"'","Humidity");
        Gson gson=new Gson();
        ArrayList<WeatherData> arraydata=new ArrayList<>();
        for (int i=0;i<date.size();i++){
            arraydata.add(new WeatherData((String)temp.get(i),(String)humidity.get(i),(String)date.get(i),(String)time.get(i)));
        }
        String sendData=gson.toJson(arraydata);
        MySQL.close();
        return sendData;
    }

    public void insertWeatherData(float temperature, float humidity){
        int result=MySQL.insert("INSERT INTO weatherdata (Temperature, Humidity) VALUES ('"+temperature+"','"+humidity+"')");
    }

}
