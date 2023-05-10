package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.repository.ActivityRepository;
import com.javarush.jira.common.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityService {

    public static final String STATUS_IN_PROGRESS = "in progress";
    public static final String STATUS_READY = "ready";
    public static final String STATUS_DONE = "done";

    ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public Long getSecondsForDevelop(Long taskId) {
        Optional<Long> durationOptionalInProgress = repository.getDurationInSeconds(taskId, STATUS_IN_PROGRESS, STATUS_READY);
        if (durationOptionalInProgress.isPresent()) {
            return durationOptionalInProgress.get();
        } else {
            throw new NotFoundException("Activity with task_id=" + taskId + " not found or task not ready");
        }
    }

    public Long getSecondsForTest(Long taskId) {
        Optional<Long> durationOptionalDone = repository.getDurationInSeconds(taskId, STATUS_READY, STATUS_DONE);
        if (durationOptionalDone.isPresent()) {
            return durationOptionalDone.get();
        } else {
            throw new NotFoundException("Activity with task_id=" + taskId + " not found or task not ready");
        }
    }
}
