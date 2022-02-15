package ownClass;


import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class ArduinoSet extends Arduino {
    int Pin;
    boolean setValue;


    public ArduinoSet(int ID,int Pin, boolean setValue) {
        super(ID);
        this.Pin = Pin;
        this.setValue = setValue;
    }

    /***
     *set the values to the arduino
     * @param setval transmit data to the arduino
     * @param setting object with ip-address and port
     * @return recived data from the arduino
     */

    public static String send(String setval,Arduino setting){
        SocketClient ardunio=new SocketClient(setting.ipAdd,setting.Port);
        String temp =ardunio.sendData(setval);
        if (!setval.equals(temp)) return "Ardunio has not Json received";
        return "Ardunio has Json received";
    }

    /***
     * check the string to see if it contains a json
     * @param setval check data
     * @return result
     */
    public static boolean isJson(String setval){
        try {
            Gson gson=new Gson();
            ArduinoSet test = gson.fromJson(setval,ArduinoSet.class);
            System.out.println(test.ID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * send a command to the arduino to switch on and of the outputs
     * @param setting Arduino settings
     */
    public static void testGPIO(Arduino setting, ArrayList<Pinout> pinout,String ArduinoID){
        ArrayList<Pinout> outputList=pinout.stream().filter(e->e.ArduinoID.equals(ArduinoID)).filter(e->e.type.contains("output")).collect(Collectors.toCollection(ArrayList::new));
        for(int i=0;i<outputList.size();i++){
            setGPIO(setting,ArduinoID,outputList.get(i).pin,300);
         }
    }

    private static void setGPIO(Arduino setting, String ArduinoID,String pin,int timeout){
        SocketClient ardunio=new SocketClient(setting.ipAdd,setting.Port);
        try{
            String temp =ardunio.sendData("{\"ID\":"+ArduinoID+",\"Pin\":"+pin+",\"setValue\":true}\n");
            Thread.sleep(timeout);
            temp =ardunio.sendData("{\"ID\":"+ArduinoID+",\"Pin\":"+pin+",\"setValue\":false}\n");
        }
        catch (Exception e) {}
    }

    public static void randomSet(Arduino setting, ArrayList<Pinout> pinout,String ArduinoID){
        ArrayList<Pinout> outputList=pinout.stream().filter(e->e.ArduinoID.equals(ArduinoID)).filter(e->e.type.contains("output")).collect(Collectors.toCollection(ArrayList::new));
        Random random = new Random();
        for(int i=0;i<10;i++){
            int temp = random.nextInt(4);
            setGPIO(setting,ArduinoID,outputList.get(temp).pin,70);
        }
    }



}
