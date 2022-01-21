package ownClass;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;


public class SocketClient {
    String ipAddress;

    public SocketClient(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String sendData(String data){
        try {
            InetAddress host = InetAddress.getByName(ipAddress);
            Socket socket = new Socket(host.getHostName(), 90);
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(data);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            in.close();
            out.close();
            socket.close();
            return in.toString();
        } catch ( Exception e){

        }
        return null;
    }
}
