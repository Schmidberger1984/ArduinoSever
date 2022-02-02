package ownClass;

import Database.Statements;
import com.google.gson.Gson;
import java.util.concurrent.*;


public final class ThreadService{
    static ScheduledExecutorService executorService=null;
    private static ThreadService INSTANCE;
    private static Arduino setting=null;

    private ThreadService(){
        executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(ThreadService::startrecord, 0, 2, TimeUnit.SECONDS);
    }


    public static void startrecord (){
        if(INSTANCE == null) {
            INSTANCE = new ThreadService();
        }
        SocketClient ardunio=new SocketClient(setting.ipAdd, setting.Port);
        String temp =ardunio.sendData("{\"ID\":"+setting.ID+",\"APin\":34}");
        Gson gson=new Gson();
        ArduinoGet data=gson.fromJson(temp,ArduinoGet.class);
        Convert convert = new Convert( data.Value);
        Statements statement= new Statements();
        statement.insertWeatherData(convert.toGrad(), convert.toHumidity(), setting.ID);
    }

      public static void destroying() {
        executorService.shutdown();
    }
    public static void setdata(Arduino data){
     setting=data;
    }

}
