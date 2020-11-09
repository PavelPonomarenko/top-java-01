package com.gmail.ponomarenko;

import com.gmail.ponomarenko.matcher.ModelMatcher;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;

public class MealTestData {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealTestData.class);


    public static final int MEAL1_ID = BaseEntity.START_SEQ + 2;

    public static final UserMeal MEAL1 = new UserMeal(MEAL1_ID, LocalDateTime.of(2015, 1, 6, 9, 0), "breakfast", 500);
    public static final UserMeal MEAL2 = new UserMeal(MEAL1_ID + 1, LocalDateTime.of(2015, 1, 6, 13, 0), "dinner", 1000);
    public static final UserMeal MEAL3 = new UserMeal(MEAL1_ID + 2, LocalDateTime.of(2015, 1, 7, 0, 0), "supper", 600);
    public static final UserMeal MEAL4 = new UserMeal(MEAL1_ID + 3, LocalDateTime.of(2015, 1, 7, 13, 0), "dinner", 1300);
    public static final UserMeal ADMIN_MEAL = new UserMeal(MEAL1_ID + 4, LocalDateTime.of(2015, 1, 6, 14, 0), "admin_meal", 2000);

    public static UserMeal getCreated() {
//        return new UserMeal(null, of(2015, 1, 8, 18, 0), "created", 300);
        return new UserMeal(MEAL1_ID + 5, of(2015, 1, 8, 18, 0), "created", 300);
    }

    public static UserMeal getUpdated() {
        UserMeal updated = new UserMeal(MEAL1);
        updated.setDescription("Updated breakfast");
        return updated;
    }


    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
