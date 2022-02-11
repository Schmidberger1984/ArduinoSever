package Database;

import ownClass.ReadConfig;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {
    static Connection connection;
    static ResultSet rs=null;
    static Statement statement=null;

    /***
     open a connection to the mysql-server
     */
    public static void connect(){
        ReadConfig config=new ReadConfig();
        System.out.println(config.isNotFound());
        if (!config.isNotFound()) {
        try {
            String dbURL = config.getUrl();
            String username = config.getUser();
            String password = config.getPassword();
            // load and register JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

    /***
     *to execute a select statement on the database
     *
     * @param cmd  sql-statemanet
     * @param cell selected column
     * @return a Arraylist with the results
     */
    public static ArrayList select(String cmd, String cell){
        ArrayList data=new ArrayList();
        try {
             statement = connection.createStatement();
             rs = statement.executeQuery(cmd);
            while (rs.next()) {
                data.add(rs.getString(cell));
            }
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    /***
     * to execute a insert statement on the database
     * @param cmd sql-statemanet
     * @return error-Code
     */

    public static int insert(String cmd){
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(cmd);
        }catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    /***
     * close the connection to the database
     */

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
