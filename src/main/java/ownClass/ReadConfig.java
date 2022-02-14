package ownClass;

import org.ini4j.Wini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadConfig {
    private String url;
    private String user;
    private String password;
    private boolean notFound =false;

    public ReadConfig(){
        readIni();
    }

    private void read() {
        File file = new File(getClass().getClassLoader().getResource("Setting.txt").getFile());  // Main ist der Name von der Klasse
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                    String line=scanner.nextLine();
                    if (line.contains("url")) url=parser(line);
                    else if (line.contains("root")) user =parser(line);
                    else if (line.contains("password")) password=parser(line);
                    else notFound =true;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private String parser(String data){
        int pos=data.indexOf('=');
        int end=data.length();
        if ((pos+1)==end) return "";
        return data.substring(pos+1,end);
    }


    private void readIni()  {
        Wini ini = null;
        try {
            ini = new Wini(new File("C:\\Software_Projekt\\ArduinoSever\\src\\main\\resources\\Setting2.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url=ini.get("MySQL","url",String.class);
        user=ini.get("MySQL","user",String.class);
        password=ini.get("MySQL","password",String.class);
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isNotFound() {
        return notFound;
    }
}
