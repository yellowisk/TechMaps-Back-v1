package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.util.*;
public class CreateTaskRequest {
    @NotNull
    private UUID taskId;
    @NotNull
    private Stage stage;
    @NotNull
    private String title;
    @NotNull
    private String link;
    @NotNull
    private Date date;
    @NotNull
    private Time hour;
    @NotNull
    private TaskCommit taskCommit;

    public CreateTaskRequest() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public TaskCommit getTaskCommit() {
        return taskCommit;
    }

    public void setTaskCommit(TaskCommit taskCommit) {
        this.taskCommit = taskCommit;
    }
}
