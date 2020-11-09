package com.gmail.ponomarenko.web;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);
    private WebApplicationContext wac;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to userList");
        UserService userService = wac.getBean(UserService.class);
        request.setAttribute("userList", userService.getAll());
        request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(request, response);
//        response.sendRedirect("userList.jsp");
    }
}
