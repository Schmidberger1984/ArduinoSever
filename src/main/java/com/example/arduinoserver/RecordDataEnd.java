package com.example.arduinoserver;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ownClass.ThreadService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RecordDataEnd", value = "/RecordDataEnd")
public class RecordDataEnd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Recording end");
        try {
            ThreadService.destroying();
        }catch (Exception e) {
            out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
