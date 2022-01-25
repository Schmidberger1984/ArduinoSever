package ownClass;

import Database.Statements;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;

public class Ardunio {
    public int ID=0;
    public String ipAdd="";
    public boolean online=false;

    public Ardunio(int ID) {
        this.ID = ID;

    }

    public Ardunio(int ID, String ipAdd, boolean online) {
        this.ID = ID;
        this.ipAdd=ipAdd;
        this.online=online;
    }


    public static ArrayList checkArduino() {
        String result = "";
        Statements sql= new Statements();
        ArrayList<String> onlineArduino = new ArrayList<>();
        Gson gson = null;
        try {
            for (Map.Entry<Integer, String> entry : sql.getArdunioList().entrySet()) {
                boolean status=true;
                InetAddress inet = InetAddress.getByName(entry.getValue());
                if (!inet.isReachable(1000)) status=false; ;
                gson = new Gson();
                gson.toJson(new Ardunio(3, "3", true));
                onlineArduino.add( gson.toJson(new Ardunio(entry.getKey(),entry.getValue(), status)));
            }
        } catch (IOException e) {
            return null;
        }
      return onlineArduino;
    }
}
