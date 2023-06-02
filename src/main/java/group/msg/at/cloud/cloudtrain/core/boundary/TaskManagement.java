package group.msg.at.cloud.cloudtrain.core.boundary;

import group.msg.at.cloud.cloudtrain.core.control.TaskRepository;
import group.msg.at.cloud.cloudtrain.core.control.UserPermissionVerifier;
import group.msg.at.cloud.cloudtrain.core.entity.Task;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Simple {@code Boundary} that manages {@code Task} entities.
 */
@Dependent
@RolesAllowed("CLOUDTRAIN_USER")
public class TaskManagement {

    @Inject
    UserPermissionVerifier verifier;
    @Inject
    TaskRepository repository;

    @NotNull
    public UUID addTask(@NotNull @Valid Task newTask) {
        verifier.requirePermission("TASK_CREATE");
        return this.repository.addTask(newTask);
    }

    public void modifyTask(@NotNull @Valid Task modifiedTask) {
        verifier.requirePermission("TASK_UPDATE");
        this.repository.setTask(modifiedTask);
    }

    public Task getTaskById(@NotNull UUID taskId) {
        Task result = this.repository.getTaskById(taskId);
        if (result != null) {
            verifier.requirePermission("TASK_READ");
        }
        return result;
    }

    public void removeTask(@NotNull UUID taskId) {
        Task found = this.repository.getTaskById(taskId);
        if (found != null) {
            verifier.requirePermission("TASK_DELETE");
            repository.removeTaskById(found.getId());
        }
    }

    public List<Task> getAllTasks() {
        verifier.requirePermission("TASK_READ");
        return this.repository.getAllTasks();
    }
}
