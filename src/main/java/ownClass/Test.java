package ownClass;



import Database.Statements;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
      Statements statement= new Statements();
        ReadConfig config=new ReadConfig();
        System.out.println(config.getUrl());
        System.out.println(config.getUser());
        System.out.println(config.getPassword());
        System.out.println(statement.getArduinoList());

    }
}

