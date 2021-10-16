package com.thoughtworks.springboot.auto.configuration.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("error page .... ");

//        resp.getWriter().write("error page");
        resp.getWriter().write("<html><body><h1>This Error Page</h1><p>This application has no explicit mapping for /error, " +
                "so you are seeing this as a fallback.</p><div id='created'>" + new Date() +
                "</div><div>There was an unexpected error (type=Not Found, status=404).</div><div></div></body></html>");
    }

}
