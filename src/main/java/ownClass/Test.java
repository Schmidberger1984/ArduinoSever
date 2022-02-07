package ownClass;


import Database.Statements;

public class Test {
    public static void main(String[] args) {
        Statements statement = new Statements();
        System.out.println(statement.insertWeatherData(25.4456,40.33356,1));
    }
}

