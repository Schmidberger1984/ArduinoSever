package ownClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadConfig {
    private String url;
    private String user;
    private String password;
    private boolean notFound =false;

    public ReadConfig() {
        read();
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
