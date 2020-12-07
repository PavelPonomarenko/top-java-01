package com.gmail.ponomarenko.web.meal;

import com.gmail.ponomarenko.Profiles;
import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.service.UserMealService;
import com.gmail.ponomarenko.web.WebTest;
import com.gmail.ponomarenko.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.gmail.ponomarenko.MealTestData.MATCHER;
import static com.gmail.ponomarenko.MealTestData.MEAL1;
import static com.gmail.ponomarenko.MealTestData.MEAL1_ID;
import static com.gmail.ponomarenko.MealTestData.MEAL2;
import static com.gmail.ponomarenko.MealTestData.MEAL3;
import static com.gmail.ponomarenko.MealTestData.MEAL4;
import static com.gmail.ponomarenko.MealTestData.getCreated;
import static com.gmail.ponomarenko.MealTestData.getUpdated;
import static com.gmail.ponomarenko.TestUtil.userHttpBasic;
import static com.gmail.ponomarenko.UserTestData.USER;
import static com.gmail.ponomarenko.model.BaseEntity.START_SEQ;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static junit.framework.TestCase.assertEquals;

@ActiveProfiles({Profiles.HSQLDB, Profiles.DATAJPA})
@Transactional
public class UserMealRestControllerTest extends WebTest {
    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Autowired
    private UserMealService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

/*
    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + (ADMIN_MEAL.getId()))
                .with(userHttpBasic(USER)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + (ADMIN_MEAL.getId())).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
*/

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
    }

    @Test
    public void testDeleteAll() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        assertEquals(0, service.getAll(START_SEQ).size());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(MEAL4, MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();

        mockMvc.perform(put(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        UserMeal created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER)));

        UserMeal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }
}
//    public static final String REST_URL = UserMealRestController.REST_URL + "/";
//
//    @Autowired
//    private UserMealService service;
//
//    @Test
//    public void testGet() throws Exception {
//        mockMvc.perform(get(REST_URL + MEAL1_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MATCHER.contentMatcher(MEAL1));
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        mockMvc.perform(delete(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
//    }
//
//    @Test
//    public void testDeleteAll() throws Exception {
//        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        assertEquals(0, service.getAll(START_SEQ).size());
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MATCHER.contentListMatcher(MEAL4, MEAL3, MEAL2, MEAL1));
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        UserMeal updated = getUpdated();
//        mockMvc.perform(put(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(updated)))
//                .andExpect(status().isOk());
//        assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        UserMeal created = getCreated();
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(created)));
//        UserMeal returned = MATCHER.fromJsonAction(action);
//        created.setId(returned.getId());
//        MATCHER.assertEquals(created, returned);
//        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
//    }

