package ownClass;

import Database.Statements;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;


public class Arduino {
    public int ID=0;
    public String ipAdd="";
    public  int Port=0;
    public boolean online=false;

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
           Arduino current= (Arduino) iter.next();
            while (iter.hasNext()) {
                InetAddress inet = InetAddress.getByName("10.0.0.12");
                if (!inet.isReachable(1000)) current.online = true;
                else System.out.println("dsfas");
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
