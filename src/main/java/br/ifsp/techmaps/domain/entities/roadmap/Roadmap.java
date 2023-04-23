package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Roadmap {
    private UUID id;
    private String title;
    private RoadmapStatus roadmapStatus;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;
    private List<Stage> stages;

    public Roadmap() {
    }

    public Roadmap(UUID id, String title, RoadmapStatus roadmapStatus,
                   LocalDateTime startTime, LocalDateTime undoneDuration)
    {
        this.id = id;
        this.title = title;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
        this.undoneDuration = undoneDuration;
    }

    public Roadmap(UUID id, String title, RoadmapStatus roadmapStatus, LocalDateTime startTime)
    {
        this.id = id;
        this.title = title;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapStatus getRoadmapStatus() {
        return roadmapStatus;
    }

    public void setRoadmapStatus(RoadmapStatus roadmapStatus) {
        this.roadmapStatus = roadmapStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getUndoneDuration() {
        return undoneDuration;
    }

    public void setUndoneDuration(LocalDateTime undoneDuration) {
        this.undoneDuration = undoneDuration;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public void createStage(StageEnum stageEnum, StageStatus stageStatus, LocalDateTime startTime) {
        Stage stage = new Stage(UUID.randomUUID(), this, stageEnum, StageStatus.UNDONE);

        if (stages == null) {
            stages = new ArrayList<>();
        } else {
            stages.add(stage);
        }

    }

    @Override
    public String toString() {
        return "Roadmap{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", roadmapStatus=" + roadmapStatus +
                ", startTime=" + startTime +
                ", undoneDuration=" + undoneDuration +
                ", stages=" + stages +
                '}';
    }
}