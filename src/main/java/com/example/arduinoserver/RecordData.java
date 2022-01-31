package com.example.arduinoserver;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.Threadnew;

import java.io.IOException;

@WebServlet(name = "RecordData", value = "/RecordData")
public class RecordData extends HttpServlet {
    @Override
    public void init(){
     Threadnew getdataThread = new Threadnew();
        getdataThread.start();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
