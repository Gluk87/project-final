package com.javarush.jira.profile.web;

import com.javarush.jira.MatcherFactory;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;

import java.util.Set;

public class ProfileTestData {
    public static final String USER_MAIL = "user@gmail.com";

    public static final MatcherFactory.Matcher<ProfileTo> PROFILE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
            ProfileTo.class);

    public static final ProfileTo profileTo = new ProfileTo(1L,
            Set.of("assigned", "deadline", "overdue"),
            Set.of(new ContactTo("skype", "userSkype"),
                    new ContactTo("mobile", "+01234567890"),
                    new ContactTo("website", "user.com")));

    public static <T> String jsonWithMailNotifications(T profileTo, Set<String> mailNotifications) {
        return JsonUtil.writeAdditionProps(profileTo, "mailNotifications", mailNotifications);
    }
}
