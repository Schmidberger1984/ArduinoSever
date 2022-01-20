package com.example.arduinoserver;

import com.google.gson.Gson;

public class ArduinoSet extends Ardunio {
    int Port =0;
    int Pin=0;
    boolean setValue=false;


    public ArduinoSet(int ID, int Port,int Pin, boolean setValue) {
        super(ID);
        this.Port = Port;
        this.Pin = Pin;
        this.setValue = setValue;
    }

    public static int send(String setval){
        Gson gson=new Gson();
        try {
            ArduinoSet input = gson.fromJson(setval, ArduinoSet.class);
            return input.ID;
        } catch (Exception e){
            System.out.println(e);

        }
        return -1;
    }

}
