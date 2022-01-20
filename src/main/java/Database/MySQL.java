package Database;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {
    static Connection connection;
    static ResultSet rs=null;

    public static void connect(){
        try {
            String dbURL = "jdbc:mysql://localhost:3306/weather";
            String username = "root";
            String password = "";
            // load and register JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ArrayList select(String cmd, String cell){
        ArrayList data=new ArrayList();
        try {
            Statement statement = connection.createStatement();
             rs = statement.executeQuery(cmd);
            while (rs.next()) {
                data.add(rs.getString(cell));
            }
            return data;
        } catch (Exception e) {

        }
        return null;
    }


    public static void close(){
        if (rs!=null){
            try{
                rs.close();
            }catch (SQLException e) {}
        }

        if (connection!=null) {
            try {
                connection.close();
            }catch (SQLException e) {}
        }
    }
}
