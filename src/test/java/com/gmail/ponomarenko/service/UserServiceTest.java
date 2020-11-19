package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.Profiles;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.DbPopulator;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.gmail.ponomarenko.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(Profiles.POSTGRES)
public class UserServiceTest {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServiceTest.class);

    @Autowired
    protected UserService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        LOG.info("| ----- Test UserServiceTest.class is started ----- |");
        dbPopulator.execute();
        service.evictCache();
    }

    @After
    public void after() {
        LOG.info("| ----- Test UserServiceTest.class is finished ----- |");
    }

    @Test
    public void testSave() throws Exception {
        TestUser tu = new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = service.save(tu.asUser());
        tu.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(ADMIN, tu, USER), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateEMailSave() throws Exception {
        service.save(new TestUser("Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER).asUser());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BaseEntity.START_SEQ);
        MATCHER.assertListEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(BaseEntity.START_SEQ);
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);

    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = service.getAll();
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        service.update(updated.asUser());
        MATCHER.assertEquals(updated, service.get(BaseEntity.START_SEQ));
    }

}
