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
    public static ArrayList<Arduino> data;

    public Arduino(int ID) {
        this.ID = ID;
    }

    public Arduino(int ID, String ipAdd, int Port, boolean online) {
        this.ID = ID;
        this.ipAdd=ipAdd;
        this.online=online;
        this.Port=Port;
    }

    /***
     * connection test for the arduino (timeout=500ms)
      * @return give a list of available arduino's return
     */

    public static ArrayList<Arduino> checkArduino() throws IOException {
        Statements sql= new Statements();
        data=sql.getArduinoList();
        Iterator<Arduino> iter=data.listIterator();
        try {
            while (iter.hasNext()) {
                Arduino current=  iter.next();
                InetAddress inet = InetAddress.getByName(current.ipAdd);
                if (inet.isReachable(500)) current.online = true;

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
