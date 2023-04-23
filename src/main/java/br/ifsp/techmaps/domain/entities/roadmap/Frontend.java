package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Frontend extends Roadmap {
    private RoadmapLanguage roadmapLanguage;
    private List<Stage> stages;

    public Frontend(UUID id, String title, RoadmapStatus roadmapStatus,
                    LocalDateTime startTime, LocalDateTime undoneDuration,
                    RoadmapLanguage roadmapLanguage, List<Stage> stages)
    {
        super(id, title, roadmapStatus, startTime, undoneDuration);
        this.roadmapLanguage = roadmapLanguage;
        this.stages = stages;
    }

    public Frontend(UUID id, String title, RoadmapStatus roadmapStatus,
                    LocalDateTime startTime, RoadmapLanguage roadmapLanguage,
                    List<Stage> stages)
    {
        super(id, title, roadmapStatus, startTime);
        this.roadmapLanguage = roadmapLanguage;
        this.stages = stages;
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



}
