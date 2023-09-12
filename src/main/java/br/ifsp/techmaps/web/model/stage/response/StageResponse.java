package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.web.model.task.response.TaskResponse;

import java.util.*;
import java.util.stream.Collectors;

public class StageResponse {
    private UUID id;
    private UUID roadmapId;
    private StageEnum theme;
    private StageStatus status;
    private List<TaskResponse> tasks;
    private int number;
    private Integer commits;

    public StageResponse(UUID id) {
        this.id = id;
    }



    public StageResponse(UUID id, UUID roadmapId,
                         StageEnum theme, StageStatus status,
                         List<TaskResponse> tasks, int number, Integer commits) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.status = status;
        this.tasks = tasks;
        this.number = number;
        this.commits = commits;
    }

    public StageResponse(UUID id, UUID roadmapId, StageEnum theme,
                         StageStatus status, int number, Integer commits) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.status = status;
        this.number = number;
        this.commits = commits;
    }

    public static StageResponse createJustId(UUID id) {
        return new StageResponse(id);
    }

    public static StageResponse createFromStage(Stage stage) {
        return new StageResponse(
                stage.getStageId(),
                stage.getRoadmap().getRoadmapId(),
                stage.getTheme(),
                stage.getStageStatus(),
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
                stage.getStageStatus(),
                taskResponses,
                stage.getNumber(),
                stage.getStageCommits()
        );
    }

    public UUID getStageId() {
        return id;
    }

    public void setStageId(UUID stageId) {
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

    public StageStatus getStatus() {
        return status;
    }

    public void setStatus(StageStatus status) {
        this.status = status;
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
