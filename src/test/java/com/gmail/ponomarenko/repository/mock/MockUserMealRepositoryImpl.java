package com.gmail.ponomarenko.repository.mock;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    public MockUserMealRepositoryImpl() {
    }

    @PostConstruct
    public void postConstructor() {
        LOG.info("PostConstructor in MockUserMealRepositoryImpl");

    }
    @PreDestroy
    public void preDestroy() {
        LOG.info("preDestroy in MockUserMealRepositoryImpl");

    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return true;
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
