package group.msg.at.cloud.cloudtrain.core.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * {@code Entity} type that represents tasks.
 *
 * @author Michael Theis (mtheis@msg.group)
 * @version 1.0
 * @since release 1.0
 */
public class Task {

    /**
     * Unique identifier of this task.
     */
    private UUID id;

    /**
     * Single-line description or summary of what this task is about.
     */
    @Size(max = 80)
    private String subject;

    /**
     * Detailed description of this task.
     */
    @Size(max = 1024)
    private String description;

    /**
     * Groups task into specific categories like "Bug", "Enhancement".
     */
    @NotNull
    private TaskCategory category = TaskCategory.UNDEFINED;

    /**
     * Priority.
     */
    @NotNull
    private TaskPriority priority = TaskPriority.UNDEFINED;

    /**
     * Status of this task.
     */
    @NotNull
    private TaskLifeCycleState lifeCycleState = TaskLifeCycleState.UNDEFINED;

    /**
     * Date/time when this task has been requested.
     * <p>
     * Expected to be set when task lifeCycleState is <code>running</code>.
     * </p>
     */
    private LocalDateTime submittedAt;

    /**
     * User-ID of participant who submitted this task.
     * <p>
     * Expected to be set when task lifeCycleState is <code>completed</code>.
     * </p>
     */
    private String submitterUserId;

    /**
     * Date/time when this task is supposed to be completed.
     */
    private LocalDateTime dueDate;

    /**
     * Completion rate in percent, ranges from 0 to 100.
     */
    private int completionRate;

    /**
     * Date/time when this task has been completed.
     * <p>
     * Expected to be set when task lifeCycleState is <code>completed</code>.
     * </p>
     */
    private LocalDateTime completionDate;

    /**
     * User-ID of participant who completed this task.
     * <p>
     * Expected to be set when task lifeCycleState is <code>completed</code>.
     * </p>
     */
    @Size(max = 16)
    private String completedByUserId;

    /**
     * User-ID of participant who is currently responsible for the completion of
     * this task.
     */
    @Size(max = 16)
    private String responsibleUserId;

    /**
     * Project-ID of the project this task is related to.
     * <p>
     * Equals {@link #affectedApplicationId} if this is a application maintenance
     * task not related to a specific project.
     * </p>
     */
    @Size(max = 32)
    private String affectedProjectId;

    /**
     * Application-ID of the application this task is related to.
     */
    @Size(max = 32)
    private String affectedApplicationId;

    /**
     * Name of the logical module this task is related to.
     */
    @Size(max = 32)
    private String affectedModule;

    /**
     * Application resource that this task is referring to.
     */
    @Size(max = 256)
    private String affectedResource;

    /**
     * Estimated effort in hours to complete this task.
     */
    private int estimatedEffort;

    /**
     * Actual effort in hours to complete this task.
     */
    private int actualEffort;

    @Size(max = 31)
    private String createdBy;

    private LocalDateTime createdAt;

    @Size(max = 31)
    private String lastModifiedBy;

    private LocalDateTime lastModifiedAt;

    public Task() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (this.id != null) {
            throw new IllegalStateException("Task ID already set!");
        }
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskLifeCycleState getLifeCycleState() {
        return lifeCycleState;
    }

    public void setLifeCycleState(TaskLifeCycleState state) {
        this.lifeCycleState = state;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime startDate) {
        this.submittedAt = startDate;
    }

    public String getSubmitterUserId() {
        return submitterUserId;
    }

    public void setSubmitterUserId(String requesterUserId) {
        this.submitterUserId = requesterUserId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(int completionRate) {
        this.completionRate = completionRate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompletedByUserId() {
        return completedByUserId;
    }

    public void setCompletedByUserId(String completedById) {
        this.completedByUserId = completedById;
    }

    public String getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(String responsibleId) {
        this.responsibleUserId = responsibleId;
    }

    public String getAffectedProjectId() {
        return affectedProjectId;
    }

    public void setAffectedProjectId(String affectedProjectId) {
        this.affectedProjectId = affectedProjectId;
    }

    public String getAffectedApplicationId() {
        return affectedApplicationId;
    }

    public void setAffectedApplicationId(String affectedApplicationId) {
        this.affectedApplicationId = affectedApplicationId;
    }

    public String getAffectedModule() {
        return affectedModule;
    }

    public void setAffectedModule(String affectedModule) {
        this.affectedModule = affectedModule;
    }

    public String getAffectedResource() {
        return affectedResource;
    }

    public void setAffectedResource(String affectedResource) {
        this.affectedResource = affectedResource;
    }

    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    public void setEstimatedEffort(int estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public int getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(int actualEffort) {
        this.actualEffort = actualEffort;
    }

    /**
     * Returns the user ID of the user that created this entity.
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Returns the creation date and time of this entity.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Tells this entity to retain the specified user ID and point in time as the
     * createdBy and the createdAt of this entity.
     * <p>
     * Usually this method is called by {@code Repository} implementations that
     * support auditable entities.
     * </p>
     */
    public void trackCreation(String creatorId, LocalDateTime creationDate) {
        if (this.createdBy == null) {
            this.createdBy = creatorId;
        }
        if (this.createdAt == null) {
            this.createdAt = creationDate;
        }
        if (this.lastModifiedBy == null) {
            this.lastModifiedBy = creatorId;
        }
        if (this.lastModifiedAt == null) {
            this.lastModifiedAt = creationDate;
        }
    }

    /**
     * Returns the user ID of the user that modified this entity the last time.
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Returns the date and time of the last modification of this entity.
     */
    public LocalDateTime getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    /**
     * Tells this entity to retain the specified user ID and point in time as the
     * lastModifiedBy and the lastModifiedAt of this entity.
     * <p>
     * Usually this method is called by {@code Repository} implementations that
     * support auditable entities.
     * </p>
     */
    public void trackModification(String lastModifierId, LocalDateTime lastModificationDate) {
        this.lastModifiedBy = lastModifierId;
        this.lastModifiedAt = lastModificationDate;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 7;
        result = prime * result + this.id.hashCode();
        return result;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        return id == other.id;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { id : " + this.id + " }";
    }
}
