package com.example.arduinoserver;

import Database.Statements;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.Arduino;
import ownClass.ThreadService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "RecordDataStart", value = "/RecordDataStart")
public class RecordDataStart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Map GetMap=request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> itr= GetMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String[]> values = itr.next();
            switch (values.getKey()) {
                case "start":
                    for (int i = 0; i< Arduino.data.size(); i++) {
                        String[] setval= values.getValue();
                        if (Arduino.data.get(i).online && Arduino.data.get(i).ID==Integer.parseInt(setval[0])){
                            Statements statement= new Statements();
                            out.println("Recording data");
                            ThreadService.setdata(Arduino.data.get(i));
                            ThreadService.settrigger(statement.getTrigger(setval[0]));
                            ThreadService.setpinout(statement.getPinout());
                            ThreadService.startrecord();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
