package com.example.arduinoserver;

import Database.MySQL;
import Database.Statements;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.transform.Source;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Weather", value = "/Weather")
public class Weather extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("test");
        Map GetMap=request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> itr= GetMap.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String, String[]> values= itr.next();
            switch (values.getKey()){
                case "currentData":
                    Statements statement= new Statements();
                    out.println(statement.getcurrentWeather());
                    break;
                case "currentHumidity":
                    Statements statement2= new Statements();
                    System.out.println("sdfaf");
                    String[] setval= values.getValue();
                    LocalDate searchDate= LocalDate.now();
                    if (setval.length!=0) out.println(statement2.getDayWeather(setval[0]));
                    else out.println(statement2.getDayWeather(String.valueOf(searchDate)));
                    break;
            }
        }
        MySQL.connect();
        ArrayList test=MySQL.select("SELECT * FROM weatherdata","Temperature");
        out.println(test.size());
        MySQL.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
