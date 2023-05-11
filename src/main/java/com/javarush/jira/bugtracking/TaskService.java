package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.model.UserBelong;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.internal.repository.UserBelongRepository;
import com.javarush.jira.bugtracking.to.ObjectType;
import com.javarush.jira.bugtracking.to.TaskTo;
import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService extends BugtrackingService<Task, TaskTo, TaskRepository> {

    private final UserRepository userRepository;
    private final UserBelongRepository userBelongRepository;
    public TaskService(TaskRepository repository, TaskMapper mapper, UserRepository userRepository, UserBelongRepository userBelongRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.userBelongRepository = userBelongRepository;
    }

    public List<TaskTo> getAll() {
        return mapper.toToList(repository.getAll());
    }

    public Task saveTag(long id, String tag) {
        Task task = repository.getExisted(id);
        Set<String> tagSet = new HashSet<>(Collections.singleton(tag));
        task.setTags(tagSet);
        return task;
    }

    public UserBelong subscribeTask(long taskId, long userId) {
        Task task = repository.getExisted(taskId);
        User user = userRepository.getExisted(userId);

        UserBelong userBelong = new UserBelong();
        userBelong.setObjectId(task.getId());
        userBelong.setObjectType(ObjectType.TASK);
        userBelong.setUserId(user.getId());
        userBelong.setUserTypeCode(user.getRoles().stream().findAny().orElse(Role.DEV).toString());
        userBelongRepository.save(userBelong);

        return userBelong;
    }
}
