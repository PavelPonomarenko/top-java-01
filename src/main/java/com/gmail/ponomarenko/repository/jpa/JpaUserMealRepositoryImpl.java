package com.gmail.ponomarenko.repository.jpa;

import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository {
    @Override
    public UserMeal save(UserMeal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public void deleteAll(int userId) {

    }

    @Override
    public UserMeal get(int id, int userId) {
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return null;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
