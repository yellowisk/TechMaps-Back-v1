package br.ifsp.techmaps.domain.entities.task;

import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID taskId;
    private Stage stage;
    private String title;
    private String description;
    private Date date;
    private Time hour;
    private TaskGit commitTag;

    public Task(UUID taskId, Stage stage, String title, Date date, Time hour) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.date = date;
        this.hour = hour;
    }

    public Task(UUID taskId, Stage stage, String title, String description, Date date, Time hour) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
    }

    public Task(UUID taskId, Stage stage, String title, String description, Date date, Time hour, TaskGit commitTag) {
        this.taskId = taskId;
        this.stage = stage;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.commitTag = commitTag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TaskGit getCommitTag() {
        return commitTag;
    }

    public void setCommitTag(TaskGit commitTag) {
        this.commitTag = commitTag;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", stage=" + stage +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", commitTag=" + commitTag +
                '}';
    }
}
