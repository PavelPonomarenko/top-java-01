package com.gmail.ponomarenko.repository;

import com.gmail.ponomarenko.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

public interface UserMealRepository {

    UserMeal save(UserMeal meal, int userId);

    boolean delete(int id, int userId);

    void deleteAll(int userId);

    UserMeal get(int id, int userId);

    List<UserMeal> getAll(int userId);

    List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
