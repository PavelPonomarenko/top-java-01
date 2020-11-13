package com.gmail.ponomarenko.repository.datajpa;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {
    private static final Sort SORT_BY_DATETIME = new Sort(Sort.Direction.DESC,"dateTime");

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    ProxyUserRepository proxyUser;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = proxyUser.findOne(userId);
        userMeal.setUser(user);
        if (userMeal.isNew()) {
        } else {
            if (get(userMeal.getId(), userId) == null) return null;
        }

        return proxy.save(userMeal);

    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id,userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        //var1
        return proxy.getByIdAndUser(id,userId);
        //var2
//        User user = proxyUser.findOne(userId);
//        return proxy.getByIdAndUser(id,user);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        //var1
//        return proxy.getAll(userId);
        //var2
//        User user = proxyUser.findOne(userId);
//        return proxy.findAllByUserOrderByDateTimeDesc(user);
        //var3
        User user = proxyUser.findOne(userId);
        return proxy.findAllByUser(user,SORT_BY_DATETIME);
    }

    @Override
    public void deleteAll(int userId) {
        proxy.deleteAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }
}
