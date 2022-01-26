package ownClass;


import com.google.gson.Gson;

public class ArduinoSet extends Ardunio {
    int Pin=0;
    boolean setValue=false;


    public ArduinoSet(int ID,int Pin, boolean setValue) {
        super(ID);
        this.Pin = Pin;
        this.setValue = setValue;
    }

    public static String send(String setval){
            SocketClient ardunio=new SocketClient("10.0.0.12");
       return ardunio.sendData(setval);
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
