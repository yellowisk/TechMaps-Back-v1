package br.ifsp.techmaps.web.model.task.response;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskBody;

import java.sql.Timestamp;
import java.util.*;

public class TaskResponse {
    private UUID taskId;
    private UUID stageId;
    private String position;
    private String title;
    private String description;
    private String repository_link;
    private Timestamp date_created;
    private Timestamp date_finished;
    private UUID dashboardId;
    private UUID commitId;

    public TaskResponse(UUID taskId, UUID stageId, String position, String title, String description,
                        String repository_link, Timestamp date_created, Timestamp date_finished,
                        UUID dashboardId, UUID commitId) {
        this.taskId = taskId;
        this.stageId = stageId;
        this.title = title;
        this.position = position;
        this.description = description;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboardId = dashboardId;
        this.commitId = commitId;
    }

    public TaskResponse(UUID taskId, UUID stageId, String title, String description,
                        String repository_link, Timestamp date_created, Timestamp date_finished,
                        UUID dashboardId) {
        this.taskId = taskId;
        this.stageId = stageId;
        this.title = title;
        this.description = description;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboardId = dashboardId;
    }

    public static TaskResponse createFromTask(Task task) {
        return new TaskResponse(task.getId(), task.getStage().getStageId(),
                task.getTaskBody().name(),
                task.getTaskBody().getTitle(), task.getTaskBody().getDescription(),
                task.getRepository(), task.getDate_created(), task.getDate_finished(),
                task.getDashboard().getDashboardId(), task.getTaskCommit().getCommitId());
    }

    public static TaskResponse createFromTaskWithoutCommit(Task task) {
        return new TaskResponse(task.getId(), task.getStage().getStageId(),
                task.getTaskBody().getTitle(), task.getTaskBody().getDescription(),
                task.getRepository(), task.getDate_created(), task.getDate_finished(),
                task.getDashboard().getDashboardId());
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public String getPosition() {
        return position.substring(position.length() - 1);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepository_link() {
        return repository_link;
    }

    public void setRepository_link(String repository_link) {
        this.repository_link = repository_link;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public Timestamp getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(Timestamp date_finished) {
        this.date_finished = date_finished;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }

    public UUID getCommitId() {
        return commitId;
    }

    public void setCommitId(UUID commitId) {
        this.commitId = commitId;
    }
}
