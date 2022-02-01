package ownClass;

import Database.Statements;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;


public class Arduino {
    public int ID=0;
    public String ipAdd="";
    public  int Port=0;
    public boolean online=false;
    public ArrayList<Arduino> ArduinoList;

    public Arduino(int ID) {
        this.ID = ID;
    }

    public Arduino(int ID, String ipAdd, int Port, boolean online) {
        this.ID = ID;
        this.ipAdd=ipAdd;
        this.online=online;
        this.Port=Port;
    }


    public static ArrayList<Arduino> checkArduino() {
        Statements sql= new Statements();
        ArrayList<Arduino> data=sql.getArdunioList();
        Iterator<Arduino> iter=data.listIterator();
        try {
           Arduino current=  iter.next();
            while (iter.hasNext()) {
                InetAddress inet = InetAddress.getByName(current.ipAdd);
                if (inet.isReachable(500)) current.online = true;
                else System.out.println("ofline");
                current=iter.next();
            }
        } catch (IOException e) {
            return null;
        }
      return data;
    }

    @Override
    public String toString() {
        return "Arduino{" +
                "ID=" + ID +
                ", ipAdd='" + ipAdd + '\'' +
                ", Port=" + Port +
                ", online=" + online +
                '}';
    }
}
