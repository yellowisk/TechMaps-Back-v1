package br.ifsp.techmaps.web.model.task.response;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;
import java.util.*;

public class TaskResponse {
    private UUID id;
    private UUID stageId;
    private String title;
    private String description;
    private int position;
    private String repository;
    private Timestamp dateCreated;
    private Timestamp dateFinished;
    private UUID dashboardId;
    private UUID commitId;

    public TaskResponse(UUID id, UUID stageId, String title, String description, int position,
                        String repository, Timestamp dateCreated, Timestamp dateFinished,
                        UUID dashboardId, UUID commitId) {
        this.id = id;
        this.stageId = stageId;
        this.title = title;
        this.description = description;
        this.position = position;
        this.repository = repository;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboardId = dashboardId;
        this.commitId = commitId;
    }

    public TaskResponse(UUID id, UUID stageId, String title, String description, int position,
                        String repository, Timestamp dateCreated, Timestamp dateFinished,
                        UUID dashboardId) {
        this.id = id;
        this.stageId = stageId;
        this.title = title;
        this.description = description;
        this.position = position;
        this.repository = repository;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboardId = dashboardId;
    }

    public static TaskResponse createFromTask(Task task) {
        return new TaskResponse(task.getTaskId(), task.getStage().getStageId(),
                task.getTaskBody().getTitle(), task.getTaskBody().getDescription(),
                task.getNumber(), task.getRepositoryLink(), task.getDate_created(),
                task.getDate_finished(), task.getDashboard().getDashboardId(),
                task.getTaskCommits().getCommitId());
    }

    public static TaskResponse createFromTaskWithoutCommit(Task task) {
        return new TaskResponse(task.getTaskId(), task.getStage().getStageId(),
                task.getTaskBody().getTitle(), task.getTaskBody().getDescription(),
                task.getNumber(), task.getRepositoryLink(), task.getDate_created(),
                task.getDate_finished(), task.getDashboard().getDashboardId());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
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

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Timestamp dateFinished) {
        this.dateFinished = dateFinished;
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
