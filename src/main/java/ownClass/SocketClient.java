package ownClass;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class SocketClient {
    String ipAddress;
    int Port=0;

    public SocketClient(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        Port = port;
    }

    public String sendData(String data){
        try {
            String intext="";
            InetAddress host = InetAddress.getByName(ipAddress);
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host.getHostName(),Port);
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
           return intext;
        } catch ( Exception e){
            return  "";
        }
    }
}
