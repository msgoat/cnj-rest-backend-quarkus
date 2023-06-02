package group.msg.at.cloud.cloudtrain.core.control;

import group.msg.at.cloud.cloudtrain.core.entity.Task;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simplest possible {@code Repository} implementation based on a Map.
 */
@Singleton
public class TaskRepository {

    private final Map<UUID, Task> tasksById = new ConcurrentHashMap<>();

    @Inject
    private Principal currentAuthenticatedUser;

    public UUID addTask(@NotNull Task newTask) {
        newTask.setId(UUID.randomUUID());
        newTask.trackCreation(currentAuthenticatedUser.getName(), LocalDateTime.now());
        tasksById.put(newTask.getId(), newTask);
        return newTask.getId();
    }

    public void setTask(@NotNull Task modifiedTask) {
        if (modifiedTask.getId() != null && tasksById.containsKey(modifiedTask.getId())) {
            modifiedTask.trackModification(currentAuthenticatedUser.getName(), LocalDateTime.now());
            tasksById.put(modifiedTask.getId(), modifiedTask);
        } else {
            throw new IllegalStateException(String.format("Expected task [%s] to exist, but it didn't!", modifiedTask));
        }
    }

    public Task getTaskById(@NotNull UUID taskId) {
        return tasksById.get(taskId);
    }

    public void removeTaskById(@NotNull UUID taskId) {
        Task removed = tasksById.remove(taskId);
        if (removed == null) {
            throw new IllegalStateException(String.format("Expected task [%s] to exist, but it didn't!", taskId));
        }
    }

    public List<Task> getAllTasks() {
        Collection<Task> found = tasksById.values();
        List<Task> result = new ArrayList<>();
        result.addAll(found);
        return result;
    }
}
