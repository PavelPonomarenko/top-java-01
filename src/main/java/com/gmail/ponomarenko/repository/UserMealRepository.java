package com.gmail.ponomarenko.repository;

import com.gmail.ponomarenko.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

public interface UserMealRepository {



        /**
         * save new meal
         *
         * @param meal
         * @return
         */
        UserMeal save(UserMeal meal, int userId);


        /**
         * delete meal by id
         *
         * @param id
         * @return
         */
        boolean delete(int id, int userId);

        /**
         * delete all meals for user
         *
         * @param userId
         * @return
         */
        void deleteAll(int userId);

        /**
         * get meal by its id
         *
         * @param id
         * @return
         */
        UserMeal get(int id, int userId);

        /**
         * get all meals
         *
         * @return
         */
        List<UserMeal> getAll(int userId);


        /**
         * filter between dates
         *
         * @param startDate
         * @param endDate
         * @param userId
         * @return
         */
        List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);


//
//    UserMeal save(UserMeal userMeal);
//
//    boolean delete(int id, int userId);
//
//    UserMeal get(int id, int userId);
//
//    List<UserMeal> getAll(int userId);
//
//    void deleteAll(int userId);
//
//    List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    }
