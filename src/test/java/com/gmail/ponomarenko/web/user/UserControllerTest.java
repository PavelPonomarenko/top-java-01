package com.gmail.ponomarenko.web.user;

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
public class UserControllerTest extends WebTest {

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"));
//                .andExpect(model().attribute("userList", hasSize(2)))
//                .andExpect(model().attribute("userList", hasItem(
//                        allOf(
//                                hasProperty("id", is(START_SEQ)),
//                                hasProperty("name", is(USER.getName()))
//                        )
//                )));
    }
}