package ownClass;


import com.google.gson.Gson;

public class ArduinoSet extends Arduino {
    int Pin=0;
    boolean setValue=false;


    public ArduinoSet(int ID,int Pin, boolean setValue) {
        super(ID);
        this.Pin = Pin;
        this.setValue = setValue;
    }

    public static String send(String setval){
        SocketClient ardunio=new SocketClient("10.0.0.9");
        String temp =ardunio.sendData(setval);
        if (!setval.equals(temp)) return "Ardunio has not Json received";
        return "Ardunio has Json received";
    }
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

}
