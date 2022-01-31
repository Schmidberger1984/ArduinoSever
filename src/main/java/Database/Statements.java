package Database;

import com.google.gson.Gson;
import ownClass.Arduino;
import ownClass.WeatherData;
import java.time.LocalDate;
import java.util.ArrayList;


public class Statements {

    public String getcurrentWeather(String ArduinoId) {
        MySQL.connect();
        ArrayList date=MySQL.select("SELECT * FROM weatherdata WHERE arduinoID="+ArduinoId,"Date");
        ArrayList time=MySQL.select("SELECT * FROM weatherdata WHERE arduinoID="+ArduinoId,"Time");
        ArrayList temp=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId ,"Temperature");
        ArrayList humidity=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId,"Humidity");
        ArrayList<String> ArduinoID=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId,"ArduinoID");
        Gson gson=new Gson();
        ArrayList<WeatherData> arraydata=new ArrayList<>();
        arraydata.add(new WeatherData(LastEntry(temp), LastEntry(humidity), LastEntry(date), LastEntry(time), ArduinoID.get(ArduinoID.size() - 1)));
        String sendData=gson.toJson(arraydata);
        MySQL.close();
        return sendData;
    }

    private String LastEntry(ArrayList data){
        return (String) data.get(data.size()-1);
    }

    public String getDayWeather(String inputdate,int ArduinoID){
        LocalDate searchDate= LocalDate.now();
        try {
            if (inputdate != null) searchDate = LocalDate.parse(inputdate);
        }catch (Exception e){
            return String.valueOf(e);
        }
        MySQL.connect();
        ArrayList date=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Date");
        ArrayList time=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Time");
        ArrayList temp=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Temperature");
        ArrayList humidity=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Humidity");
        ArrayList<String> ArduinoId=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Humidity");
        if (date.size()>0) {
            Gson gson = new Gson();
            ArrayList<WeatherData> arraydata = new ArrayList<>();
            for (int i = 0; i < date.size(); i++) {
                arraydata.add(new WeatherData((String) temp.get(i), (String) humidity.get(i), (String) date.get(i),(String) time.get(i),ArduinoId.get(i)));
            }
            String sendData = gson.toJson(arraydata);
            MySQL.close();
            return sendData;
        }return "No data found";
    }

    public int insertWeatherData(double temperature, double humidity, int ArduinoId){
        MySQL.connect();
        return MySQL.insert("INSERT INTO weatherdata (Temperature, Humidity,ArduinoID) VALUES ('"+temperature+"','"+humidity+"','"+ArduinoId+"')");
    }

    public ArrayList<Arduino> getArdunioList(){
        MySQL.connect();
        ArrayList<String> id=MySQL.select("SELECT * FROM arduinolist", "ID");
        ArrayList<String> ipAdd=MySQL.select("SELECT * FROM arduinolist", "ipADD");
        ArrayList<String> Port=MySQL.select("SELECT * FROM arduinolist", "Port");
        ArrayList<Arduino> data=new ArrayList();
        for(int i=0;i<id.size();i++){
           data.add(new Arduino(Integer.parseInt(id.get(i)),ipAdd.get(i),Integer.parseInt(Port.get(i)),false));
        }
        MySQL.close();
        return data;
    }

}
