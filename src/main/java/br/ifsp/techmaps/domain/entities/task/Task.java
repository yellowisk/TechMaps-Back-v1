package br.ifsp.techmaps.domain.entities.task;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskId;
    @ManyToOne
    private Stage stage;
    private String title;
    private String description;
    private String repository_link;
    private Timestamp date_created;
    private Timestamp date_finished;
    @OneToOne
    private TaskCommit taskCommit;


    public Task(UUID taskId, Stage stage, String title,
                String description, String repository_link,
                Timestamp date_created, Timestamp date_finished) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.description = description;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
    }

    public Task(UUID taskId, Stage stage, String title, String description,
                String repository_link, Timestamp date_created,
                Timestamp date_finished, TaskCommit taskCommit) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.description = description;
        this.repository_link = repository_link;
        this.date_created = date_created;
        this.date_finished = date_finished;
        this.taskCommit = taskCommit;
    }

    public Task(UUID taskId, Stage stage,
                String title, String description,
                Timestamp date_created,
                Timestamp date_finished) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.description = description;
        this.date_created = date_created;
        this.date_finished = date_finished;
    }

    private Task(UUID taskId) {
        this.taskId = taskId;
    }

    public static Task createWithOnlyId(UUID taskId) {
        return new Task(taskId);
    }

    public Task() {}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getRepository_Link() {return repository_link;}

    public void setRepository_Link(String repository_link) {this.repository_link = repository_link;}

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

}