package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.LoggedUser;
import com.gmail.ponomarenko.TestUtil;
import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import com.gmail.ponomarenko.web.WebTest;
import com.gmail.ponomarenko.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static com.gmail.ponomarenko.Profiles.DATAJPA;
import static com.gmail.ponomarenko.Profiles.HSQLDB;
import static com.gmail.ponomarenko.UserTestData.ADMIN;
import static com.gmail.ponomarenko.UserTestData.MATCHER;
import static com.gmail.ponomarenko.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({HSQLDB, DATAJPA})
public class ProfileRestControllerTest extends WebTest {

    //    public static final String REST_URL = "/rest/profile/";
    public static final String REST_URL = ProfileRestController.REST_URL + '/';
    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(ADMIN), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(LoggedUser.id(), "newName", "newEmail", "newPassword", true, Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, new User(service.get(LoggedUser.id())));
    }
}

