package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.DbPopulator;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.gmail.ponomarenko.UserTestData.*;

abstract public class UserServiceTest extends DbTest {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServiceTest.class);

////    @Autowired
//    protected UserService userService;
    @Autowired
    DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        userService.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        TestUser tu = new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = userService.save(tu.asUser());
        tu.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(ADMIN, tu, USER), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateEMailSave() throws Exception {
        userService.save(new TestUser("Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER).asUser());
    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(BaseEntity.START_SEQ);
        MATCHER.assertListEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = userService.get(BaseEntity.START_SEQ);
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = userService.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);

    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = userService.getAll();
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        userService.update(updated.asUser());
        MATCHER.assertEquals(updated, userService.get(BaseEntity.START_SEQ));
    }

}