package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.gmail.ponomarenko.MealTestData.*;
import static com.gmail.ponomarenko.model.BaseEntity.START_SEQ;

abstract public class UserMealServiceTest extends DbTest {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceTest.class);

    @Autowired
    UserMealService service;


    @Test
    public void delete() throws Exception {
        service.delete(MEAL1_ID, START_SEQ);
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID, 1);
    }

    @Test
    public void save() throws Exception {
        UserMeal created = getCreated();
        service.save(created, START_SEQ);
        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }

    @Test
    public void get() throws Exception {
        UserMeal actual = service.get(MEAL1_ID, START_SEQ);
        MATCHER.assertEquals(MEAL1, actual);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID, START_SEQ + 1);
    }

    @Test
    public void update() throws Exception {
        UserMeal updated = getUpdated();
        service.update(updated, START_SEQ);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }

    @Test
    public void getBetween() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL2, MEAL1),
                service.getBetween(LocalDateTime.of(2015, 1, 6, 8, 0), LocalDateTime.of(2015, 1, 6, 14, 0), START_SEQ));
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll(START_SEQ);
        List<UserMeal> userMeals = service.getAll(START_SEQ);
        Assert.assertEquals(0, service.getAll(START_SEQ).size());
    }

    @Test(expected = AssertionError.class)
    public void tryingSaveDuplicateMeal() {
        int startNum = service.getAll(START_SEQ).size();
        service.save(MEAL1, START_SEQ);
        service.save(MEAL2, START_SEQ);
        service.save(MEAL3, START_SEQ);
        Assert.assertNotEquals("------- the size of the database is expected to be different ------"
                , startNum, service.getAll(START_SEQ).size());
    }

    @Test(expected = NotFoundException.class)
    public void updateNonExistentEntity() {
        service.deleteAll(START_SEQ);
        System.out.println(service.getAll(START_SEQ).size());

        UserMeal updated = getUpdated();
        service.update(updated, START_SEQ);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
    }
}
