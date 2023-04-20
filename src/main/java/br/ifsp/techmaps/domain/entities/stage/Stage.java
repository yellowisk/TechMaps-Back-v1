package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;

import java.util.UUID;

public class Stage {
    private UUID stageId;
    private Roadmap roadmap;
    private StageEnum stageEnum;
    private StageStatus stageStatus;

    public Stage(UUID stageId, Roadmap roadmap, StageEnum stageEnum, StageStatus stageStatus) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.stageEnum = stageEnum;
        this.stageStatus = stageStatus;
    }

    public Stage(Roadmap roadmap, StageEnum stageEnum, StageStatus stageStatus) {
        this.roadmap = roadmap;
        this.stageEnum = stageEnum;
        this.stageStatus = stageStatus;
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

    public StageEnum getStageEnum() {
        return stageEnum;
    }

    public void setStageEnum(StageEnum stageEnum) {
        this.stageEnum = stageEnum;
    }

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
    }



}
