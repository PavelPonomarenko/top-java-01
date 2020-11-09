package com.gmail.ponomarenko.model;

import com.gmail.ponomarenko.util.TimeUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class UserMeal extends BaseEntity {
    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    private int userId;

    private User user;


    public UserMeal() {
    }



    public UserMeal(UserMeal meal) {
        this(meal.id, meal.dateTime, meal.description, meal.calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String meal) {
        this.description = meal;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + TimeUtil.toString(dateTime) + ", '" + description + "', calories:" + calories + ')';
    }

    public void setUser(User user) {
        this.user = user;
    }



//    public UserMeal(UserMeal meal) {
//        this(meal.id, meal.dateTime, meal.description, meal.calories);
//    }
//
//    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
//        super(id);
//        this.dateTime = dateTime;
//        this.description = description;
//        this.calories = calories;
//    }
//
//    public UserMeal(Integer id, LocalDateTime dateTime, int userId, String description, int calories) {
//        super(id);
//        this.dateTime = dateTime;
////        this.dateTime = this.dateTime.minusHours(1);
//        this.description = description;
//        this.calories = calories;
//        this.userId = userId;
//    }
//
//
//    public UserMeal(LocalDateTime dateTime, Integer userId, String description, int calories) {
//        this.dateTime = dateTime;
//        this.userId = userId;
//        this.description = description;
//        this.calories = calories;
//    }
//
//    public Date getDateTime() {
//        return Timestamp.valueOf(dateTime);
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String meal) {
//        this.description = meal;
//    }
//
//    public int getCalories() {
//        return calories;
//    }
//
//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public void setDateTime(Timestamp dateTime) {
//        this.dateTime = dateTime.toLocalDateTime();
//        this.dateTime.plusHours(1);
//    }
//
//    public void setCalories(int calories) {
//        this.calories = calories;
//    }
//
//    @Override
//    public String toString() {
//        return "Meal(" + id + ", " + TimeUtil.toString(dateTime) + ", '" + description + "', calories:" + calories + ')';
//    }
}
