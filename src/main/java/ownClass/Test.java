package ownClass;


import Database.Statements;

public class Test
{
    public static void main(String[] args) {
        WeatherData test = new WeatherData("2.2","77.5","43","dsf","2");
        System.out.println(test.humidity);
        Statements statement2= new Statements();
        statement2.getDayWeather("2022-02-01",2);
    }
    }

