package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.model.UserBelong;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.internal.repository.UserBelongRepository;
import com.javarush.jira.bugtracking.to.ObjectType;
import com.javarush.jira.bugtracking.to.TaskTo;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserRepository;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

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

    public void saveTag(long id, Set<@Size(min = 2, max = 32) String> tags) {
        Task task = repository.getExisted(id);
        if (!tags.isEmpty()) {
            task.getTags().addAll(tags);
            repository.save(task);
        }
    }

    public void subscribeTask(long taskId, long userId) {
        Task task = repository.getExisted(taskId);
        User user = userRepository.getExisted(userId);

        UserBelong userBelong = new UserBelong();
        userBelong.setObjectId(task.getId());
        userBelong.setObjectType(ObjectType.TASK);
        userBelong.setUserId(user.getId());
        userBelong.setUserTypeCode(user.getRoles().stream().findFirst().toString());
        userBelongRepository.save(userBelong);
    }
}
