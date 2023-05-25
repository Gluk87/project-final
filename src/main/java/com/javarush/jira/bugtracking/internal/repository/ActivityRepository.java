package com.javarush.jira.bugtracking.internal.repository;

import com.javarush.jira.bugtracking.internal.model.Activity;
import com.javarush.jira.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ActivityRepository extends BaseRepository<Activity> {

    @Query(value = "select extract(epoch from (a2.updated - a1.updated)) \n" +
            "from activity a1, activity a2 \n" +
            "where a1.task_id = a2.task_id \n" +
            "and a1.task_id = ?1 \n" +
            "and a1.status_code = ?2 \n" +
            "and a2.status_code = ?3",
           nativeQuery = true)
    Optional<Long> getDurationInSeconds(Long taskId, String status1, String status2);
}
