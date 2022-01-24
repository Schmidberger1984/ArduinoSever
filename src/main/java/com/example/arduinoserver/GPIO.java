package com.example.arduinoserver;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.ArduinoGet;
import ownClass.ArduinoSet;
import ownClass.Ardunio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "GPIO", value = "/GPIO")
public class GPIO extends HttpServlet {
    @Override
    public void init(){
        try {
            Ardunio.checkArduino();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Map GetMap=request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> itr= GetMap.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String, String[]> values= itr.next();
            switch (values.getKey()){
                case "Set":
                    String[] setval= values.getValue();
                    //out.println(setval[0]);
                    if (ArduinoSet.isJson(setval[0])) {
                        out.println(ArduinoSet.send(setval[0]));
                    }else {
                        out.println("no Json received");
                    }
                    break;
                case "Read":
                    String[] getval= values.getValue();
                    //out.println(setval[0]);
                    if (ArduinoSet.isJson(getval[0])) {
                        out.println(ArduinoGet.Get(getval[0]));
                    }else {
                        out.println("no Json received");
                    }
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
