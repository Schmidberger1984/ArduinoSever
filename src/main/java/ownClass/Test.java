package ownClass;


import Database.Statements;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Test {
    public static void main(String[] args) throws IOException {
        Wini ini = new Wini(new File("C:\\Software_Projekt\\ArduinoSever\\src\\main\\resources\\Setting2.ini"));
        String test=ini.get("MySQL","url",String.class);
        System.out.println(test);
    }
}

