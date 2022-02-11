package Database;

import com.google.gson.Gson;
import ownClass.Arduino;
import ownClass.Pinout;
import ownClass.TriggerSettings;
import ownClass.WeatherData;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class Statements {

    /***
     * open a connection to database,
     * reads the last line of the weatherdata from database out,
     * close the connection
     * @param ArduinoId selected Arduino
     * @return WeatherData in a json-format
     */

    public String getcurrentWeather(String ArduinoId) {
        MySQL.connect();
        ArrayList date=MySQL.select("SELECT * FROM weatherdata WHERE arduinoID="+ArduinoId,"Date");
        ArrayList time=MySQL.select("SELECT * FROM weatherdata WHERE arduinoID="+ArduinoId,"Time");
        ArrayList temp=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId ,"Temperature");
        ArrayList humidity=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId,"Humidity");
        ArrayList<String> ArduinoID=MySQL.select("SELECT * FROM weatherdata  WHERE arduinoID="+ArduinoId,"ArduinoID");
        Gson gson=new Gson();
        ArrayList<WeatherData> arraydata=new ArrayList<>();
        arraydata.add(new WeatherData(LastEntry(temp),LastEntry(humidity),LastEntry(date),LastEntry(time),LastEntry(ArduinoID)));
        String sendData=gson.toJson(arraydata);
        MySQL.close();
        return sendData;
    }


    private String LastEntry(ArrayList data){
        return (String) data.get(data.size()-1);
    }

    /***
     * open a connection to database,
     * reads the weatherdata from the choosen date out,
     * close the connection
     * @param inputdate search date
     * @param ArduinoID selected Arduino
     * @return Arraylist from type WeatherData in json-format
     */

    public String getDayWeather(String inputdate,int ArduinoID){
        LocalDate searchDate= LocalDate.now();
        try {
            if (inputdate != null) searchDate = LocalDate.parse(inputdate);
        }catch (Exception e){
            return String.valueOf(e);
        }
        MySQL.connect();
        ArrayList<String> date=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Date");
        ArrayList<String> time=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Time");
        ArrayList<String> temp=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Temperature");
        ArrayList<String> humidity=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"Humidity");
        ArrayList<String> ArduinoId=MySQL.select("SELECT * FROM weatherdata WHERE Date='"+searchDate+"' and ArduinoID="+ArduinoID,"ArduinoID");
        if (date.size()>0) {
            Gson gson = new Gson();
            ArrayList<WeatherData> arraydata = new ArrayList<>();
            for (int i = 0; i < date.size(); i++) {
                    arraydata.add(new WeatherData(temp.get(i), humidity.get(i), date.get(i), time.get(i), ArduinoId.get(i)));
            }
            String sendData = gson.toJson(arraydata);
            MySQL.close();
            return sendData;
        }return "No data found";
    }

    /***
     * open a connection to database,
     * write the data to the database,
     * close the connection
     * @param temperature
     * @param humidity
     * @param ArduinoId
     * @return error-code
     */

    public int insertWeatherData(double temperature, double humidity, int ArduinoId){
        MySQL.connect();
        int error=MySQL.insert("INSERT INTO weatherdata (Temperature, Humidity,ArduinoID) VALUES ('"+trimmedValue(temperature)+"','"+trimmedValue(humidity)+"','"+ArduinoId+"')");
        MySQL.close();
        return error;
    }

    private String trimmedValue(double data){
        DecimalFormat trimmed = new DecimalFormat("#.##");
        String text=String.valueOf(trimmed.format(data));
        text=text.replace(",",".");
        return text;
    }

    /***
     * open a connection to database,
     * reads a list of Arduinos out with settings for the connection
     * close the connection
     * @return list of all Arduino's
     */

    public ArrayList<Arduino> getArduinoList(){
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

    /**
     * open a connection to database,
     * reads a list of pinouts out,
     * close the connection
     * @return list of all pinouts
     */

    public ArrayList<Pinout> getPinout(){
        ArrayList<Pinout> pinoutList=new ArrayList<>();
        MySQL.connect();
        ArrayList<String> type=MySQL.select("SELECT * FROM arduinogpio", "Type");
        ArrayList<String> pin=MySQL.select("SELECT * FROM arduinogpio", "Pin");
        ArrayList<String> arduinoID=MySQL.select("SELECT * FROM arduinogpio", "ArduinoID");
        for (int i=0;i<type.size();i++){
            pinoutList.add(new Pinout(type.get(i), pin.get(i),arduinoID.get(i)));
        }
        MySQL.close();
        return pinoutList;
    }

    /**
     * open a connection to database,
     * reads a list of triggers out,
     * close the connection
     * @param ArduinoID
     * @return
     */

    public static TriggerSettings getTrigger(String ArduinoID){
        MySQL.connect();
        ArrayList<String> arduinoID=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "ArduinoID");
        ArrayList<String> onTemp=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "onTemp");
        ArrayList<String>  offTemp=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "offTemp");
        ArrayList<String>  onHumid=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "onHumid");
        ArrayList<String>  offHumid=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "offHumid");
        ArrayList<String>  outSide=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "outSide");
        ArrayList<String>  enable=MySQL.select("SELECT * FROM arduinotrigger WHERE ArduinoID='"+ArduinoID+"'", "enable");
        TriggerSettings data=new TriggerSettings(arduinoID.get(0), Double.valueOf(onTemp.get(0)), Double.valueOf(offTemp.get(0)), Double.valueOf(onHumid.get(0)), Double.valueOf(offHumid.get(0)), Boolean.parseBoolean(outSide.get(0)),Boolean.parseBoolean(enable.get(0)));
        MySQL.close();
        return data;

    }

}
