package com.example.arduinoserver;

import Database.MySQL;

public class Test {
    public static void main(String[] args) {
        MySQL.connect();
        System.out.println(MySQL.select("SELECT * FROM weatherdata","Temperature"));
        MySQL.close();
        ArduinoSet arduinoserver=new ArduinoSet(1,1,1, true);
        /*Gson gson=new Gson();
        String setval="""
        {\"ID\":9,\"Port\":0,\"Pin\":7}""";
        ArduinoSet input=gson.fromJson(setval,ArduinoSet.class);
        System.out.println(input.ID);*/
    }
}
