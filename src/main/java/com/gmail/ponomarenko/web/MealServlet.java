package com.gmail.ponomarenko.web;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.service.UserMealService;
import com.gmail.ponomarenko.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MealServlet extends HttpServlet {

    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);
    private WebApplicationContext web;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        web = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("print table Meals for current user");

        UserMealService mealService = web.getBean(UserMealService.class);
        UserService userService = web.getBean(UserService.class);
        request.setAttribute("allMeals", mealService.getAll(BaseEntity.START_SEQ));
        request.setAttribute("userName", userService.get(BaseEntity.START_SEQ).getName());
        request.getRequestDispatcher("/WEB-INF/jsp/mealList.jsp").forward(request, response);
    }
}
