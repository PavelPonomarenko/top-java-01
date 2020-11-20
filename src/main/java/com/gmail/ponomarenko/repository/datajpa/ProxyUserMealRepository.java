package com.gmail.ponomarenko.repository.datajpa;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {


    @Transactional
    @Modifying
    UserMeal save(UserMeal meal, int userId);

    //    @Query(name = User.DELETE)
    @Modifying
    @Transactional
    @Query(name = UserMeal.DELETE)
    boolean delete(int id, int userId);

    @Modifying
    @Transactional
    @Query(name = UserMeal.DELETE_ALL)
    void deleteAll(int userId);

    @Query(name = UserMeal.GET)
    UserMeal get(int id, int userId);

    @Query(name = UserMeal.DELETE_ALL)
    List<UserMeal> getAll(int userId);

    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
