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

    @Autowired
    DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
        userService.evictCache();
    }

    @Test
    public void save() {
        TestUser tu = new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = userService.save(tu.asUser());
        tu.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(ADMIN, tu, USER), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void duplicateEMailSave() {
        userService.save(new TestUser("Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER).asUser());
    }

    @Test
    public void delete() {
        userService.delete(BaseEntity.START_SEQ);
        MATCHER.assertListEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(BaseEntity.START_SEQ);
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = userService.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userService.getAll();
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void update() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        userService.update(updated.asUser());
        MATCHER.assertEquals(updated, userService.get(BaseEntity.START_SEQ));
    }
}