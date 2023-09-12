package br.ifsp.techmaps.domain.entities.task;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskId;
    @ManyToOne
    @JsonIgnore
    private Stage stage;
    private TaskBody taskBody;
    private int number;
    private String repositoryLink;
    private Timestamp date_created;
    private Timestamp date_finished;
    @OneToOne
    @JsonIgnore
    private Dashboard dashboard;
    @OneToOne
    private TaskCommit taskCommits;

    public Task(UUID taskId, Stage stage, TaskBody taskBody, int number,
                String repositoryLink, Timestamp date_created, Timestamp date_finished,
                Dashboard dashboard, TaskCommit taskCommits) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.number = number;
        this.repositoryLink = repositoryLink;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
        this.taskCommits = taskCommits;
    }

    public Task(UUID taskId, Stage stage, TaskBody taskBody, int number,
                String repositoryLink, Timestamp date_created, Timestamp date_finished,
                Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.number = number;
        this.repositoryLink = repositoryLink;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, Stage stage, String repositoryLink, Timestamp date_created, Timestamp date_finished, Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.repositoryLink = repositoryLink;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(Stage stage, TaskBody taskBody, String repositoryLink, Timestamp date_created, Timestamp date_finished, Dashboard dashboard) {
        this.stage = stage;
        this.taskBody = taskBody;
        this.repositoryLink = repositoryLink;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, String repositoryLink, Timestamp date_created) {
        this.taskId = taskId;
        this.repositoryLink = repositoryLink;
        this.date_created = date_created;
    }

    public Task(String repositoryLink, Timestamp date_finished) {
        this.repositoryLink = repositoryLink;
        this.date_finished = date_finished;
    }

    public Task(UUID taskId, Dashboard dashboard) {
        this.taskId = taskId;
        this.dashboard = dashboard;
    }

    public Task(String repositoryLink) {
        this.repositoryLink = repositoryLink;
    }

    public Task(Timestamp date_finished) {
        this.date_finished = date_finished;
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