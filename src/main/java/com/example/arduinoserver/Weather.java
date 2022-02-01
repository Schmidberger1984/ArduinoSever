package com.example.arduinoserver;

import Database.Statements;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.WeatherData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "Weather", value = "/Weather")
public class Weather extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Map GetMap=request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> itr= GetMap.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String, String[]> values= itr.next();
            switch (values.getKey()){
                case "currentData":
                    String[] setval= values.getValue();
                    Statements statement= new Statements();
                    out.println(statement.getcurrentWeather(setval[0]));
                    break;
                case "dayData":
                    Statements statement2= new Statements();
                    String[] setval1= values.getValue();
                    Gson gson = new Gson();
                    WeatherData data =gson.fromJson(setval1[0],WeatherData.class);
                    if (data==null) out.println("No Json found");
                    else {
                        try {
                            out.println(statement2.getDayWeather(data.date, data.ID));
                        }catch (Exception e){
                            System.out.println(e);
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
