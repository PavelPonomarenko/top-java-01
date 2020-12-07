package com.gmail.ponomarenko.web.meal;

import com.gmail.ponomarenko.web.WebTest;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.gmail.ponomarenko.Profiles.DATAJPA;
import static com.gmail.ponomarenko.Profiles.HSQLDB;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ActiveProfiles({HSQLDB, DATAJPA})
public class UserMealControllerTest extends WebTest {

    @Test
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userMealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userMealList.jsp"));
////                .andExpect(model().attribute("userMealList", hasSize(4)))
////                .andExpect(model().attribute("userMealList", hasItem(
////                        allOf(
////                                hasProperty("id", is(MEAL1_ID)),
////                                hasProperty("description", is(MEAL1.getDescription()))
////                        )
//                )));
    }
}