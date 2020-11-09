package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.util.DbPopulator;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.gmail.ponomarenko.MealTestData.*;
import static com.gmail.ponomarenko.model.BaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
public class UserMealServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    UserMealService service;

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
    public void testDelete() throws Exception {
        service.delete(MEAL1_ID, START_SEQ);
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
    }

    //    @Test(expected = NotFoundException.class)
    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        UserMeal created = getCreated();
        UserMeal u = service.save(created, START_SEQ);
//        System.err.println(u.toString());
        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));

    }

    @Test
    public void testGet() throws Exception {
        UserMeal actual = service.get(MEAL1_ID, START_SEQ);
        System.err.println(actual.toString());


        MATCHER.assertEquals(MEAL1, actual);
    }

    //    @Test(expected = NotFoundException.class)
    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID, START_SEQ + 1);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        service.update(updated, START_SEQ);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
    }

    //    @Test(expected = NotFoundException.class)
    @Test
    public void testNotFoundUpdate() throws Exception {
        UserMeal item = service.get(MEAL1_ID, START_SEQ);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MEAL1_ID);
        service.update(item, START_SEQ + 1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL2, MEAL1),
                service.getBetween(LocalDateTime.of(2015, 1, 6, 8, 0), LocalDateTime.of(2015, 1, 6, 14, 0), START_SEQ));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(START_SEQ);
        Assert.assertEquals(0, service.getAll(START_SEQ).size());
    }


    //
//    @Test
//    public void saveMeal() throws Exception {
//        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
//        UserMeal created = service.save(tm);
//        tm.setId(created.getId());
//        MATCHER.assertListEquals(Arrays.asList(tm, USER_MEAL_TEMP_ONE), service.getAll(100000));
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void saveDuplicateMealUnit() throws Exception {
//        service.save(new UserMeal(LocalDateTime.now(), 1111, "No user", 1111));
//    }
//
//    @Test
//    public void updateMeal() throws Exception {
//        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
//        UserMeal created = service.save(tm);
//        tm.setId(created.getId());
//        tm.setCalories(777);
//        service.update(tm, 100000);
//        MATCHER.assertEquals(tm, service.get(tm.getId(), tm.getUserId()));
//    }
//
//    @Test(expected = EmptyResultDataAccessException.class)
//    public void updateMealForeign() throws Exception {
//        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "borsh", 333);
//        service.update(tm, 100000);
//        MATCHER.assertEquals(tm, service.get(tm.getId(), 100001));
//    }
//
//    @Test
//    public void deleteMeal() throws Exception {
//        service.save(new UserMeal(LocalDateTime.now(), 100001, "for Delete", 1111));
//        System.out.println(service.get(BaseEntity.START_SEQ + 4, 100001).toString());
//        service.delete(BaseEntity.START_SEQ + 4, 100001);
//        MATCHER.assertListEquals(Collections.singletonList(USER_MEAL_TEMP_TWO), service.getAll(100001));
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void deleteForeignMeal() throws Exception {
//        service.delete(BaseEntity.START_SEQ + 2, 100001);
//    }
//
//    @Test
//    public void deleteAllMeal() throws Exception {
//        service.deleteAll(100000);
//        Assert.assertTrue(service.getAll(100000).size() == 0);
//    }
//
//    @Test
//    public void getOneMeal() throws Exception {
//        MATCHER.assertEquals(USER_MEAL_TEMP_TWO, service.get(BaseEntity.START_SEQ + 3, 100001));
//    }
//
//    @Test
//    public void getMealForeign() throws EmptyResultDataAccessException {
//        MATCHER.assertEquals(USER_MEAL_TEMP_TWO, service.get(BaseEntity.START_SEQ + 3, 100001));
//    }
//
//    @Test
//    public void getAllMeal() throws Exception {
//        UserMeal tm = new UserMeal(LocalDateTime.now(), 100000, "break", 100);
//        UserMeal created = service.save(tm);
//        tm.setId(created.getId());
//        MATCHER.assertListEquals(Arrays.asList(tm, USER_MEAL_TEMP_ONE), service.getAll(100000));
//    }
//
//    @Test
//    public void getBetweenPeriod() throws Exception {
//        LocalDateTime now = LocalDateTime.now();
//        List<UserMeal> meals = service.getBetween(now.minusDays(1), now.plusDays(1), 100000);
//        ArrayList<UserMeal> userMeals = new ArrayList<>();
//        userMeals.add(USER_MEAL_TEMP_ONE);
//        MATCHER.assertListEquals(userMeals, meals);
//    }
}
