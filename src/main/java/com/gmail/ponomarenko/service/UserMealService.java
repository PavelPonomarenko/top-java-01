package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

public interface UserMealService {

    public UserMeal save(UserMeal meal, int userId);

    public UserMeal update(UserMeal meal, int userId);

    public void delete(int id, int userId);

    public void deleteAll(int userId);

    public UserMeal get(int id, int userId);

    public List<UserMeal> getAll(int userId);

    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
