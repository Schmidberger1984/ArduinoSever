package ownClass;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Ardunio {
    public int ID=0;
    static Map<Integer,String> ArdunioList=new HashMap<>();

    public Ardunio(int ID) {
        this.ID = ID;
    }

     public static  Map<Integer, String> getArdunioList() {
        ArdunioList.put(1,"10.0.0.12");
        ArdunioList.put(2,"10.0.0.14");
        return ArdunioList;
    }
    public static boolean checkArduino() throws IOException {
        for(Map.Entry<Integer,String> entry : ArdunioList.entrySet()){
            InetAddress inet=InetAddress.getByName(entry.getValue());
            if(!inet.isReachable(1000)) return false;
        }
        return true;
    }


}
