package com.gmail.ponomarenko;

import com.gmail.ponomarenko.matcher.ModelMatcher;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;

import java.util.function.Function;

public class MealTestData {


    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(
            new Function<UserMeal, String>() {
                @Override
                public String apply(UserMeal meal) {
                    return meal.toString();
                }
            });


}
