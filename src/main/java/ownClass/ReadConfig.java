package ownClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadConfig {
    String url;
    String user;
    String password;
    boolean notFound =false;

    public ReadConfig() {
        read();
    }

    public void read() {
        File file = new File(getClass().getClassLoader().getResource("Setting.txt").getFile());  // Main ist der Name von der Klasse
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                    String temp=scanner.nextLine();
                    if (temp.contains("url")) url=parser(temp);
                    else if (temp.contains("root")) user =parser(temp);
                    else if (temp.contains("password")) password=parser(temp);
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
