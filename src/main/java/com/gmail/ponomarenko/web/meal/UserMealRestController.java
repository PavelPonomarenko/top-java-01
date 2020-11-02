package com.gmail.ponomarenko.web.meal;

import com.gmail.ponomarenko.LoggedUser;
import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.UserMeal;

import com.gmail.ponomarenko.service.UserMealService;
import com.gmail.ponomarenko.service.UserMealServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public UserMealRestController() {
    }


    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<UserMeal> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public void deleteAll() {
        int userId = LoggedUser.id();
        LOG.info("deleteAll  for user {}", userId);
        service.deleteAll(userId);
    }

    public void update(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("delete  {} for user {}", meal, userId);
        service.update(meal, userId);
    }

    private UserMeal create(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("create  {} for user {}", meal, userId);
        return service.save(meal, userId);
    }

    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime andDate) {
        int userId = LoggedUser.id();
        LOG.info("getBetween {} and {} for user {}", startDate, andDate, userId);
        return service.getBetween(startDate, andDate, userId);
    }
}