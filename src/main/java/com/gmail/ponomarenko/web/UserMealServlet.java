package com.gmail.ponomarenko.web;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.service.UserMealService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserMealServlet extends HttpServlet {

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServlet.class);
    private WebApplicationContext wac;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to userMealList");
        UserMealService userMealService = wac.getBean(UserMealService.class);

        request.setAttribute("mealList", userMealService.getAll(BaseEntity.START_SEQ));
        request.getRequestDispatcher("/WEB-INF/jsp/userMealList.jsp").forward(request, response);
    }
}
