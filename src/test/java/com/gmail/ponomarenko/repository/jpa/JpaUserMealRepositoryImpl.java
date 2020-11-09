package com.gmail.ponomarenko.repository.jpa;

import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {


//    @Override
//    @Transactional
//    public UserMeal save(UserMeal userMeal) {
//        return null;
//    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return false;
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
    public void deleteAll(int userId) {

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
