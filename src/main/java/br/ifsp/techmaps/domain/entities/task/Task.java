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

    private String repository_link;
    private Timestamp date_created;
    private Timestamp date_finished;
    @OneToOne
    @JsonIgnore
    private Dashboard dashboard;
    @OneToOne
    private TaskCommit taskCommit;

    public Task(UUID taskId, Stage stage, TaskBody taskBody, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard, TaskCommit taskCommit) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
        this.taskCommit = taskCommit;
    }

    public Task(UUID taskId, Stage stage, TaskBody taskBody, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.taskBody = taskBody;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, Stage stage, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard) {
        this.taskId = taskId;
        this.stage = stage;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(Stage stage, TaskBody taskBody, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard) {
        this.stage = stage;
        this.taskBody = taskBody;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.dashboard = dashboard;
    }

    public Task(UUID taskId, String repository_link, Timestamp date_created) {
        this.taskId = taskId;
        this.repository_link = repository_link;
        this.date_created = date_created;
    }

    public Task(String repository_link, Timestamp date_finished) {
        this.repository_link = repository_link;
        this.date_finished = date_finished;
    }

    public Task(String repository_link) {
        this.repository_link = repository_link;
    }

    public Task(Timestamp date_finished) {
        this.date_finished = date_finished;
    }

    private Task(UUID taskId) {
        this.taskId = taskId;
    }

    public Task() {}

    public static Task createWithOnlyId(UUID taskId) {
        return new Task(taskId);
    }

    public static Task createFull(UUID taskId, Stage stage, TaskBody taskBody, String repository_link, Timestamp date_created, Timestamp date_finished, Dashboard dashboard, TaskCommit taskCommit) {
        return new Task(taskId, stage, taskBody, repository_link, date_created, date_finished, dashboard, taskCommit);
    }

    public static Task createForStage(UUID taskId, String repository_link, Timestamp date_created) {
        return new Task(taskId, repository_link, date_created);
    }

    public UUID getId() {
        return taskId;
    }

    public void setId(UUID taskId) {
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

    public String getRepository() {
        return repository_link;
    }

    public void setRepository(String repository_link) {
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

    public TaskCommit getTaskCommit() {
        return taskCommit;
    }

    public void setTaskCommit(TaskCommit taskCommit) {
        this.taskCommit = taskCommit;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}