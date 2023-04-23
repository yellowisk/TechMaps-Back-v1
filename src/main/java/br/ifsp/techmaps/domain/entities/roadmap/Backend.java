package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.stage.StageType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Backend extends Roadmap{
    private RoadmapLanguage roadmapLanguage;
    private List<Stage> stages;

    public Backend(UUID id, String title, RoadmapStatus roadmapStatus,
                   LocalDateTime startTime, LocalDateTime undoneDuration,
                   RoadmapLanguage roadmapLanguage)
    {
        super(id, title, roadmapStatus, startTime, undoneDuration);
        this.roadmapLanguage = roadmapLanguage;
    }

    public Backend(UUID id, String title, RoadmapStatus roadmapStatus,
                   LocalDateTime startTime, RoadmapLanguage roadmapLanguage)
    {
        super(id, title, roadmapStatus, startTime, null);
        this.roadmapLanguage = roadmapLanguage;
    }

    public Backend(UUID id, String title, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        super(id, title, roadmapStatus, startTime);
    }

    public static Backend createFrontend(String title, RoadmapStatus roadmapStatus,
                                          LocalDateTime startTime, RoadmapLanguage roadmapLanguage)
    {
        if (roadmapLanguage == null || roadmapLanguage.equals(RoadmapLanguage.JAVA) || roadmapLanguage.equals(RoadmapLanguage.PYTHON)) {
            return new Backend(UUID.randomUUID(), title, roadmapStatus, startTime, roadmapLanguage);
        } else {
            System.out.println("Language not allowed");
            return new Backend(UUID.randomUUID(), title, roadmapStatus, startTime);
        }
    }

    public RoadmapLanguage getRoadmapLanguage() {
        return roadmapLanguage;
    }

    public void setRoadmapLanguage(RoadmapLanguage roadmapLanguage) {
        this.roadmapLanguage = roadmapLanguage;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public void createStage(StageEnum stageEnum, StageStatus stageStatus, LocalDateTime startTime) {
        StageType stageType = new StageType();

        if (stageType.getBackList().contains(stageEnum) || stageType.getGeneralList().contains(stageEnum)) {
            super.createStage(stageEnum, stageStatus, startTime);
            System.out.println("Stage allowed");
        } else {
            System.out.println("Stage not allowed");
        }
    }

    @Override
    public String toString() {
        return "Backend{" +
                "roadmapLanguage=" + roadmapLanguage +
                ", stages=" + stages +
                '}';
    }
}
