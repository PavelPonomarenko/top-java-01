package com.gmail.ponomarenko.web;


import com.gmail.ponomarenko.LoggerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {

    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

//        request.getRequestDispatcher("/userList.jsp").forward(request, response);
        response.sendRedirect("mealList.jsp");
    }
}
