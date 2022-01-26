package com.example.arduinoserver;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.SocketClient;

import java.io.IOException;

@WebServlet(name = "RecordData", value = "/RecordData")
public class RecordData extends HttpServlet {
    @Override
    public void init(){
        SocketClient socket=new SocketClient("10.0.0.12");
        while (true) {
            socket.sendData("{\"ID\":1,\"APIN\":22}");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}
