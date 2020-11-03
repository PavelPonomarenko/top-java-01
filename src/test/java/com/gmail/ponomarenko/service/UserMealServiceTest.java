package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.util.DbPopulator;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.gmail.ponomarenko.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        System.out.println("----> Test started <----");
        dbPopulator.execute();
    }

    @After
    public void after() {
        System.out.println("----> Test finished <----");
    }

    @Test
    public void saveMeal() throws Exception {
        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
        UserMeal created = service.save(tm);
        tm.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(tm, USER_MEAL_TEMP_ONE), service.getAll(100000));
    }

    @Test(expected = DataAccessException.class)
    public void saveDuplicateMealUnit() throws Exception {
        service.save(new UserMeal(LocalDateTime.now(), 1111, "No user", 1111));
    }

    @Test
    public void updateMeal() throws Exception {
        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
        UserMeal created = service.save(tm);
        tm.setId(created.getId());
        tm.setCalories(777);
        service.update(tm, 100000);
        MATCHER.assertEquals(tm, service.get(tm.getId(), tm.getUserId()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateMealForeign() throws Exception {
        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "borsh", 333);
        service.update(tm, 100000);
        MATCHER.assertEquals(tm, service.get(tm.getId(), 100001));
    }

    @Test
    public void deleteMeal() throws Exception {
        service.save(new UserMeal(LocalDateTime.now(), 100001, "for Delete", 1111));
        System.out.println(service.get(BaseEntity.START_SEQ + 4, 100001).toString());
        service.delete(BaseEntity.START_SEQ + 4, 100001);
        MATCHER.assertListEquals(Collections.singletonList(USER_MEAL_TEMP_TWO), service.getAll(100001));
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeignMeal() throws Exception {
        service.delete(BaseEntity.START_SEQ + 2, 100001);
    }

    @Test
    public void deleteAllMeal() throws Exception {
        service.deleteAll(100000);
        Assert.assertTrue(service.getAll(100000).size() == 0);
    }

    @Test
    public void getOneMeal() throws Exception {
        MATCHER.assertEquals(USER_MEAL_TEMP_TWO, service.get(BaseEntity.START_SEQ + 3, 100001));
    }

    @Test
    public void getMealForeign() throws EmptyResultDataAccessException {
        MATCHER.assertEquals(USER_MEAL_TEMP_TWO, service.get(BaseEntity.START_SEQ + 3, 100001));
    }

    @Test
    public void getAllMeal() throws Exception {
        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
        UserMeal created = service.save(tm);
        tm.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(tm, USER_MEAL_TEMP_ONE), service.getAll(100000));
    }

    @Test
    public void getBetweenPeriod() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        List<UserMeal> meals = service.getBetween(now.minusDays(1), now.plusDays(1), 100000);
        ArrayList<UserMeal> userMeals = new ArrayList<>();
        userMeals.add(USER_MEAL_TEMP_ONE);
        MATCHER.assertListEquals(userMeals, meals);
    }
}
