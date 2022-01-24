package ownClass;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class SocketClient {
    String ipAddress;

    public SocketClient(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String sendData(String data){
        try {
            String intext="";
            InetAddress host = InetAddress.getByName(ipAddress);
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host.getHostName(),90);
            socket.connect(socketAddress,1000);
            socket.setSoTimeout(1000);
            if (socket.isConnected()) {
                PrintStream out = new PrintStream(socket.getOutputStream());
                out.println(data);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                intext = in.readLine();
                in.close();
                out.close();
                socket.close();
            }
            String response = null;
            if (data.equals(intext)) {
            response = "Arduino has a json received";}
            return response;
        } catch ( Exception e){
            return  "Ardunio has nothing received ";
        }
    }
}
