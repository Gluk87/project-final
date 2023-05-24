package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.repository.ActivityRepository;
import com.javarush.jira.common.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ActivityService {

    public static final String STATUS_IN_PROGRESS = "in progress";

    public static final String STATUS_READY = "ready";

    public static final String STATUS_DONE = "done";

    private final ActivityRepository repository;

    public Long getTimeForDevelopInSeconds(Long taskId) {
        return repository.getDurationInSeconds(taskId, STATUS_IN_PROGRESS, STATUS_READY)
                .orElseThrow(()-> new NotFoundException("Activity with task_id=" + taskId + " not found or task not ready"));
    }

    public Long getTimeForTestInSeconds(Long taskId) {
        return repository.getDurationInSeconds(taskId, STATUS_READY, STATUS_DONE)
                .orElseThrow(()-> new NotFoundException("Activity with task_id=" + taskId + " not found or task not ready"));
    }
}
