package br.ifsp.techmaps.domain.entities.task;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.sql.Timestamp;
import java.util.UUID;

public class Task {
    private UUID taskId;
    private Stage stage;
    private TaskBody taskBody;
    private int number;
    private String repositoryLink;
    private Timestamp dateCreated;
    private Timestamp dateFinished;
    private Dashboard dashboard;
    private TaskCommit taskCommits;

    public Task(UUID taskId, Stage stage, TaskBody taskBody, int number,
                String repositoryLink, Timestamp dateCreated, Timestamp dateFinished,
                Dashboard dashboard, TaskCommit taskCommits) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.number = number;
        this.repositoryLink = repositoryLink;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboard = dashboard;
        this.taskCommits = taskCommits;
    }

    public Task(UUID taskId, Stage stage, TaskBody taskBody, int number,
                String repositoryLink, Timestamp dateCreated, Timestamp dateFinished,
                Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.number = number;
        this.repositoryLink = repositoryLink;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, Stage stage, String repositoryLink, Timestamp dateCreated,
                Timestamp dateFinished, Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.repositoryLink = repositoryLink;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboard = dashboard;
    }

    public Task(Stage stage, TaskBody taskBody, String repositoryLink, Timestamp dateCreated,
                Timestamp dateFinished, Dashboard dashboard) {
        this.stage = stage;
        this.taskBody = taskBody;
        this.repositoryLink = repositoryLink;
        this.dateCreated = dateCreated;
        this.dateFinished = dateFinished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, String repositoryLink, Timestamp dateCreated) {
        this.taskId = taskId;
        this.repositoryLink = repositoryLink;
        this.dateCreated = dateCreated;
    }

    public Task(String repositoryLink, Timestamp dateFinished) {
        this.repositoryLink = repositoryLink;
        this.dateFinished = dateFinished;
    }

    public Task(UUID taskId, Dashboard dashboard) {
        this.taskId = taskId;
        this.dashboard = dashboard;
    }

    public Task(String repositoryLink) {
        this.repositoryLink = repositoryLink;
    }

    public Task(Timestamp dateFinished) {
        this.dateFinished = dateFinished;
    }

    public Task(int number) {
        this.number = number;
    }

    private Task(UUID taskId) {
        this.taskId = taskId;
    }

    public Task() {}

    public static Task createWithIdAndDashboard(UUID taskId, Dashboard dashboard) {
        return new Task(taskId, dashboard);
    }

    public static Task createWithOnlyId(UUID taskId) {
        return new Task(taskId);
    }

    public static Task createFull(UUID taskId, Stage stage, TaskBody taskBody, int number, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard, TaskCommit taskCommit) {
        return new Task(taskId, stage, taskBody, number, repository_link, date_created, date_finished, dashboard, taskCommit);
    }

    public static Task createForStage(UUID taskId, String repository_link, Timestamp date_created) {
        return new Task(taskId, repository_link, date_created);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TaskBody getTaskBody() {
        return taskBody;
    }

    public void setTaskBody(TaskBody taskBody) {
        this.taskBody = taskBody;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
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

    public TaskCommit getTaskCommits() {
        return taskCommits;
    }

    public void setTaskCommits(TaskCommit taskCommits) {
        this.taskCommits = taskCommits;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", position=" + number +
                '}';
    }
}