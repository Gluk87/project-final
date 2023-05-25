package com.javarush.jira.profile.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.javarush.jira.profile.web.ProfileRestController.REST_URL;
import static com.javarush.jira.profile.web.ProfileTestData.PROFILE_MATCHER;
import static com.javarush.jira.profile.web.ProfileTestData.USER_MAIL;
import static com.javarush.jira.profile.web.ProfileTestData.jsonWithMailNotifications;
import static com.javarush.jira.profile.web.ProfileTestData.profileTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PROFILE_MATCHER.contentJson(profileTo));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        ProfileTo updated = profileTo;
        Set<String> mailNotifications = new HashSet<>(List.of("assigned", "deadline", "overdue"));

        updated.setMailNotifications(mailNotifications);
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithMailNotifications(updated, mailNotifications)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
