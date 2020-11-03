package com.gmail.ponomarenko;

import com.gmail.ponomarenko.matcher.ModelMatcher;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;

import java.time.LocalDateTime;
import java.util.function.Function;

public class MealTestData {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealTestData.class);

    public static final UserMeal USER_MEAL_TEMP_ONE = new UserMeal(BaseEntity.START_SEQ + 2, LocalDateTime.now(), 100000, "soup", 123);

    public static final UserMeal USER_MEAL_TEMP_TWO = new UserMeal(BaseEntity.START_SEQ + 3, LocalDateTime.now(), 100001, "salad", 321);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(
            new Function<UserMeal, String>() {
                @Override
                public String apply(UserMeal meal) {
                    return meal.toString();
                }
            });

}
