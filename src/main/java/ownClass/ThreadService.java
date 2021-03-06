package ownClass;

import Database.Statements;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.*;


public final class ThreadService{
    static ScheduledExecutorService executorService=null;
    private static ThreadService INSTANCE;
    private static Arduino setting=null;
    private static  TriggerSettings triggerSettings=null;
    private static ArrayList<Pinout> pinoutList=null;

    private ThreadService(){
        executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(ThreadService::startrecord, 0, 2, TimeUnit.SECONDS);
    }

    /***
     * start a thread and every 2 second a read command is sent to the arduino
     * after receiving data and converting the raw data
     */

    public static void startrecord ()  {
        if (INSTANCE == null) {
            INSTANCE = new ThreadService();
        }

        Optional<Pinout> pintemp = pinoutList.stream().filter(e -> e.ArduinoID.equals(String.valueOf(setting.ID))).filter(e -> e.type.equals("temperature")).findFirst();
        Optional<Pinout> pinhumidity = pinoutList.stream().filter(e -> e.ArduinoID.equals(String.valueOf(setting.ID))).filter(e -> e.type.equals("humidity")).findFirst();
        Optional<Pinout> pinout = pinoutList.stream().filter(e -> e.ArduinoID.equals(String.valueOf(setting.ID))).filter(e -> e.type.equals("output1")).findFirst();

        if(pintemp.isPresent() && pinhumidity.isPresent() && pinout.isPresent() ){
            SocketClient ardunio = new SocketClient(setting.ipAdd, setting.Port);
            String temp = ardunio.sendData("{\"ID\":" + setting.ID + ",\"APin\":" + pintemp.get().pin + "}");
            Gson gson = new Gson();
            ArduinoGet dataTemp = gson.fromJson(temp, ArduinoGet.class);
            String temp2 = ardunio.sendData("{\"ID\":" + setting.ID + ",\"APin\":" + pinhumidity.get().pin + "}");
            ArduinoGet datahumid = gson.fromJson(temp2, ArduinoGet.class);
            Convert convert = new Convert(dataTemp.Value, datahumid.Value);
            Statements statement = new Statements();
            statement.insertWeatherData(convert.toGrad(), convert.toHumidity(), setting.ID);
            if (triggerSettings.enable) {
                if (triggerSettings.outside) {
                    if (convert.toGrad() > triggerSettings.onTemp || convert.toHumidity() > triggerSettings.onHumid)
                        ardunio.sendData("{\"ID\":\"+setting.ID+\",\"Pin\":" + pinout.get().pin + ",\"setValue\":true}");
                    else if (convert.toGrad() < triggerSettings.offTemp || convert.toHumidity() < triggerSettings.offHumid)
                        ardunio.sendData("{\"ID\":\"+setting.ID+\",\"Pin\":" + pinout.get().pin + ",\"setValue\":false}");
                } else {
                    if ((convert.toGrad() < triggerSettings.onTemp && convert.toGrad() > triggerSettings.offTemp) || (convert.toHumidity() < triggerSettings.onHumid && convert.toHumidity() > triggerSettings.offHumid))
                        ardunio.sendData("{\"ID\":\"+setting.ID+\",\"Pin\":" + pinout.get().pin + ",\"setValue\":true}");
                    else ardunio.sendData("{\"ID\":\"+setting.ID+\",\"Pin\":" + pinout.get().pin + ",\"setValue\":false}");
                }
            }
        }
    }

    /***
     * destroy the thread
     */

    public static void destroying() {
        INSTANCE=null;
        executorService.shutdown();
    }

    /***
     * fetch the current values and stored them in a local variable
     * @param data current data
     */
    public static void setdata(Arduino data){
     setting=data;
    }

    /**
     * fetch the current values and stored them in a local variable
     * @param data current data
     */
    public static void settrigger(TriggerSettings data){
        triggerSettings=data;
    }

    /**
     * fetch the current values and stored them in a local variable
     * @param data current data
     */

    public static void setpinout(ArrayList<Pinout> data){
        pinoutList=data;
    }

}
