package com.example.arduinoserver;


import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.ArduinoGet;
import ownClass.ArduinoSet;
import ownClass.Ardunio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "GPIO", value = "/GPIO")
public class GPIO extends HttpServlet {
    ArrayList onlineList;

    @Override
    public void init() {
        onlineList =Ardunio.checkArduino();
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
                        for (int i=0;i<onlineList.size();i++){
                            Gson gson= new Gson();
                            Gson gson2= new Gson();
                            Ardunio test=gson.fromJson((String) onlineList.get(i),Ardunio.class);
                            Ardunio test2=gson.fromJson(setval[0],Ardunio.class);
                            if (test.ID==test2.ID) out.println("sdaf");
                        }
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
                case "Status":
                    out.println(onlineList);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
