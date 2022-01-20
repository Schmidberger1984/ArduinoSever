package com.example.arduinoserver;

public class ArduinoGet extends Ardunio{
    int Port =0;
    int Pin=0;
    int APin =0;


    public ArduinoGet(int ID, int Port, int Pin) {
        super(ID);
        this.Port = Port;
        this.Pin = Pin;
    }

    public ArduinoGet(int ID, int APin) {
        super(ID);
        this.APin = APin;
    }

}
