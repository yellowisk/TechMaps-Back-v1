package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.web.model.task.response.TaskResponse;

import java.util.*;
import java.util.stream.Collectors;

public class StageResponse {
    private UUID stageId;
    private UUID roadmapId;
    private StageEnum theme;
    private StageStatus stageStatus;
    private List<TaskResponse> tasks;
    private int number;
    private Integer stageCommit;

    public StageResponse(UUID stageId) {
        this.stageId = stageId;
    }



    public StageResponse(UUID stageId, UUID roadmapId,
                         StageEnum theme, StageStatus stageStatus,
                         List<TaskResponse> tasks, int number, Integer stageCommit) {
        this.stageId = stageId;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.tasks = tasks;
        this.number = number;
        this.stageCommit = stageCommit;
    }

    public StageResponse(UUID stageId, UUID roadmapId, StageEnum theme,
                         StageStatus stageStatus, int number, Integer stageCommit) {
        this.stageId = stageId;
        this.roadmapId = roadmapId;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.number = number;
        this.stageCommit = stageCommit;
    }

    public static StageResponse createJustId(UUID stageId) {
        return new StageResponse(stageId);
    }

    public static StageResponse createFromStage(Stage stage) {
        return new StageResponse(
                stage.getStageId(),
                stage.getRoadmap().getRoadmapId(),
                stage.getTheme(),
                stage.getStageStatus(),
                stage.getNumber(),
                stage.getStageCommit()
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
                stage.getStageCommit()
        );
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
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

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
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

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }
}
