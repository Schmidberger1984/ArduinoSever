package ownClass;

import Database.MySQL;
import Database.Statements;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class Test {
    public static void main(String[] args) {
        Statements sql = new Statements();
        System.out.println(sql.insertWeatherData(5.33,2.3,1));

    }
}
