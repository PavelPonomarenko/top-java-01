package com.gmail.ponomarenko.model;

import com.gmail.ponomarenko.util.TimeUtil;

import java.time.LocalDateTime;

public class UserMeal extends BaseEntity {
    protected LocalDateTime dateTime;
    protected String description;
    protected int calories;
    private User user;

    public UserMeal() {
        super();
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    @Override
    public String toString() {
        return " UserMeal{" + id +
                " dateTime=" + TimeUtil.toString(dateTime) +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
