package ownClass;


import Database.Statements;

import java.util.ArrayList;


public class Test {
    public static void main(String[] args) {
        Statements statement= new Statements();
        System.out.println(statement.getPinout());
        ArduinoSet.testGPIO(new Arduino(1,"10.0.0.12",5000,true),statement.getPinout(),"1");
      /*  ArduinoSet.testGPIO(new Arduino(1,"10.0.0.12",5000,true),statement.getPinout(),"1");
        ArrayList<Pinout>  pinoutList;
                pinoutList= statement.getPinout();
        Pinout[] pin=pinoutList.stream().filter(e->e.ArduinoID.equals("1")).filter(e->e.type.equals("temperature")).toArray(Pinout[]::new);
        System.out.println(pin.length);*/
    }
}

