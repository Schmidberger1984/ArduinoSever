package com.example.arduinoserver;

import Database.MySQL;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Weather", value = "/Weather")
public class Weather extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("test");
        MySQL.connect();
        List test=MySQL.select("SELECT * FROM weatherdata","Temperature");
        out.println(test.size());
        // out.println(MySQL.select("SELECT * FROM weatherdata","Humidity"));
        MySQL.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
