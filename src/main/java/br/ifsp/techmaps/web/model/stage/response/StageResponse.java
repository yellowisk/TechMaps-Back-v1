package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.task.Task;

import java.util.List;
import java.util.UUID;

public class StageResponse {
    private UUID stageId;
    private Roadmap roadmap;
    private StageEnum theme;
    private StageStatus stageStatus;
    private List<Task> tasks;
    private Integer stageCommit;

    public StageResponse(UUID stageId) {
        this.stageId = stageId;
    }

    public static StageResponse createJustId(UUID stageId) {
        return new StageResponse(stageId);
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public Roadmap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(Roadmap roadmap) {
        this.roadmap = roadmap;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }
}
