package com.example.arduinoserver;


import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.ArduinoGet;
import ownClass.ArduinoSet;
import ownClass.Arduino;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "GPIO", value = "/GPIO")
public class GPIO extends HttpServlet {
    public ArrayList<Arduino> onlineList;
    String onlineJson;

    @Override
    public void init() {
        onlineList = Arduino.checkArduino();
        Gson gson2= new Gson();
        onlineJson=gson2.toJson(onlineList);
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
                        Gson gson= new Gson();
                        Arduino postobj=gson.fromJson(setval[0], Arduino.class);
                        for (int i=0;i<onlineList.size();i++){
                            if (onlineList.get(i).ID==postobj.ID && onlineList.get(i).online==true){
                                out.println(ArduinoSet.send(setval[0],onlineList.get(i)));
                            }
                            if (onlineList.get(i).ID==postobj.ID && onlineList.get(i).online==false) out.println("Arduino is offline");
                        }
                    }else {
                        out.println("no Json received");
                    }
                    break;
                case "Read":
                    String[] getval= values.getValue();
                    Gson gson2= new Gson();
                    Arduino postobj2=gson2.fromJson(getval[0], Arduino.class);
                    if (ArduinoSet.isJson(getval[0])) {
                        for (int i=0;i<onlineList.size();i++) {
                            if (onlineList.get(i).ID == postobj2.ID && onlineList.get(i).online == true) {
                                out.println(ArduinoGet.getValue(getval[0], onlineList.get(i)));
                            }
                        }
                    }else {
                        out.println("no Json received");
                    }
                    break;
                case "Status":
                    out.println(onlineJson);
                    break;

                case "TestGPIO":
                    String[] testval= values.getValue();
                        for (int i=0;i<onlineList.size();i++) {
                            if (onlineList.get(i).ID == Integer.valueOf(testval[0]) && onlineList.get(i).online == true) {
                                out.println("Start GPIO-Test");
                                ArduinoSet.testGPIO(onlineList.get(i));
                            }
                        }
                    out.println("Finished GPIO-Test");
                    break;

            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
