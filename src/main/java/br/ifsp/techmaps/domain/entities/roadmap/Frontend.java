package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.stage.StageType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Frontend extends Roadmap {
    private RoadmapLanguage roadmapLanguage;
    private List<Stage> stages;

    public Frontend(UUID id, String title, RoadmapStatus roadmapStatus,
                    LocalDateTime startTime, LocalDateTime undoneDuration,
                    RoadmapLanguage roadmapLanguage)
    {
        super(id, title, roadmapStatus, startTime, undoneDuration);
        this.roadmapLanguage = roadmapLanguage;
    }

    public Frontend(UUID id, String title, RoadmapStatus roadmapStatus,
                    LocalDateTime startTime, RoadmapLanguage roadmapLanguage)
    {
        super(id, title, roadmapStatus, startTime);
                this.roadmapLanguage = roadmapLanguage;

    }

    public Frontend(UUID id, String title, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        super(id, title, roadmapStatus, startTime);
    }

    public static Frontend createFrontend(String title, RoadmapStatus roadmapStatus,
                                          LocalDateTime startTime, RoadmapLanguage roadmapLanguage)
    {
        if (roadmapLanguage == null || roadmapLanguage.equals(RoadmapLanguage.JAVASCRIPT)) {
         return new Frontend(UUID.randomUUID(), title, roadmapStatus, startTime, roadmapLanguage);
        } else {
            System.out.println("Language not allowed");
            return new Frontend(UUID.randomUUID(), title, roadmapStatus, startTime);
        }
    }

    @Override
    public void createStage(StageEnum stageEnum, StageStatus stageStatus, LocalDateTime startTime) {
        StageType stageType = new StageType();

            if (stageType.getFrontList().contains(stageEnum) || stageType.getGeneralList().contains(stageEnum)) {
                super.createStage(stageEnum, stageStatus, startTime);
                System.out.println("Stage allowed");
            } else {
                System.out.println("Stage not allowed");
            }
    }

    @Override
    public String toString() {
        return "Frontend{" +
                "roadmapLanguage=" + roadmapLanguage +
                ", stages=" + stages +
                '}';
    }
}
