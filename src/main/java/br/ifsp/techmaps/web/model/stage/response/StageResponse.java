package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.web.model.task.response.TaskResponse;

import java.util.*;
import java.util.stream.Collectors;

public class StageResponse {
    private UUID id;
    private UUID roadmapId;
    private StageEnum theme;
    private boolean isDone;
    private List<TaskResponse> tasks;
    private int number;
    private Integer commits;

    public StageResponse(UUID id) {
        this.id = id;
    }

    public StageResponse(UUID id, UUID roadmapId,
                         StageEnum theme, boolean isDone,
                         List<TaskResponse> tasks, int number, Integer commits) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.isDone = isDone;
        this.tasks = tasks;
        this.number = number;
        this.commits = commits;
    }

    public StageResponse(UUID id, UUID roadmapId, StageEnum theme,
                         boolean isDone, int number, Integer commits) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.isDone = isDone;
        this.number = number;
        this.commits = commits;
    }

    public StageResponse() {
    }

    public static StageResponse createJustId(UUID id) {
        return new StageResponse(id);
    }

    public static StageResponse createFromStage(Stage stage) {
        return new StageResponse(
                stage.getStageId(),
                stage.getRoadmap().getRoadmapId(),
                stage.getTheme(),
                stage.isDone(),
                stage.getNumber(),
                stage.getStageCommits()
        );
    }

    public static StageResponse createForStage(Stage stage) {
        List<TaskResponse> taskResponses = stage.getTasks().stream()
                .map(TaskResponse::createFromTask)
                .collect(Collectors.toList());
        return new StageResponse(
                stage.getStageId(),
                stage.getRoadmap().getRoadmapId(),
                stage.getTheme(),
                stage.isDone(),
                taskResponses,
                stage.getNumber(),
                stage.getStageCommits()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID stageId) {
        this.id = id;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID roadmapId) {
        this.roadmapId = roadmapId;
    }

    public StageEnum getTheme() {
        return theme;
    }

    public void setTheme(StageEnum theme) {
        this.theme = theme;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }
}
