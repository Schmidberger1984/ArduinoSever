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
import java.util.concurrent.atomic.AtomicBoolean;


public class Test implements Runnable {
    public static Threadnew test;
    private static final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
        String test = "{\"ID\":2,\"date\":\"2022-01-31\"}";
        Gson gson = new Gson();
        WeatherData i =gson.fromJson(test,WeatherData.class);
        System.out.println(i.date);
        System.out.println(i.ID);
        start();
        Thread.sleep(10000);
        stop();
    }
    public static  void start() {
        test=new Threadnew();
        test.start();
    }

    public static void stop() {
        test.currentThread().interrupt();
    }
    public void interrupt() {
        running.set(false);
        test.interrupt();
    }

    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                Thread.sleep(1000);
                System.out.println("test");
            } catch (Exception e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            // do something
        }
        stopped.set(true);
    }
}
