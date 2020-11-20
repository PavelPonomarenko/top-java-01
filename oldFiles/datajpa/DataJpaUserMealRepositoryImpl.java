package com.gmail.ponomarenko.repository.datajpa;

import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {
    @Autowired
    private ProxyUserMealRepository proxy;
//    @Autowired
//    private ProxyUserRepository userProxy;


    @Override
    public UserMeal save(UserMeal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId);
    }

    @Override
    public void deleteAll(int userId) {
        proxy.deleteAll(userId);
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }

}
