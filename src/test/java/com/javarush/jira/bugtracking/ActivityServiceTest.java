package com.javarush.jira.bugtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ActivityServiceTest {

    public static final Long DURATION_DEVELOP = 451322L;
    public static final Long DURATION_TEST = 2860629L;

    @Autowired
    private ActivityService service;

    @Test
    void checkDurationDevelop() {
        Long durationDevelop = service.getSecondsForDevelop(4L);
        Assertions.assertEquals(DURATION_DEVELOP, durationDevelop);
    }

    @Test
    void checkDurationTest() {
        Long durationTest = service.getSecondsForTest(4L);
        Assertions.assertEquals(DURATION_TEST, durationTest);
    }
}
