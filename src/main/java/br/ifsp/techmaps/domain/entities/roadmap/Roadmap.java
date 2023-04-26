package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.stage.StageType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roadmap")
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private RoadmapType type;
    private RoadmapStatus roadmapStatus;
    private RoadmapLanguage roadmapLanguage;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;

    @OneToMany
    private List<Stage> stages;

    public Roadmap() {
    }

    public Roadmap(UUID id, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime,
                   LocalDateTime undoneDuration)
    {
        this.id = id;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.undoneDuration = undoneDuration;
    }

    public Roadmap(UUID id, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime)
    {
        this.id = id;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
    }

    public Roadmap(UUID id, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus, LocalDateTime startTime)
    {
        this.id = id;
        this.title = title;
        this.type = roadmapType;
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

    public RoadmapType getType() {
        return type;
    }

    public void setType(RoadmapType type) {
        this.type = type;
    }

    public RoadmapStatus getRoadmapStatus() {
        return roadmapStatus;
    }

    public void setRoadmapStatus(RoadmapStatus roadmapStatus) {
        this.roadmapStatus = roadmapStatus;
    }


    public RoadmapLanguage getRoadmapLanguage() {
        return roadmapLanguage;
    }

    public void setRoadmapLanguage(RoadmapLanguage roadmapLanguage) {
        this.roadmapLanguage = roadmapLanguage;
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
        StageType stageType = new StageType();

        if (this.type == RoadmapType.FRONTEND)
            if (stageType.getFrontList().contains(stageEnum) || stageType.getGeneralList().contains(stageEnum)) {
                if (stages == null) {
                    stages = new ArrayList<>();
                    stages.add(stage);
                } else {
                    stages.add(stage);
                }
                System.out.println("Stage " + stageEnum + " allowed");
            } else {
                System.out.println("Stage " + stageEnum +  " not allowed");
            }
        else if (this.type == RoadmapType.BACKEND) {
            if (stageType.getBackList().contains(stageEnum) || stageType.getGeneralList().contains(stageEnum)) {
                if (stages == null) {
                    stages = new ArrayList<>();
                    stages.add(stage);
                } else {
                    stages.add(stage);
                }
                System.out.println("Stage " + stageEnum + " allowed");
            } else {
                System.out.println("Stage " + stageEnum +  " not allowed");
            }
        }

    }

    public static Roadmap createFrontend(String title,
                                          RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                          LocalDateTime startTime)
    {
            CollectRoadmapType collectRoadmapType = new CollectRoadmapType();
            if (roadmapLanguage == null || collectRoadmapType.getFrontList().contains(roadmapLanguage)) {
                return new Roadmap(UUID.randomUUID(), title, RoadmapType.FRONTEND, roadmapStatus, roadmapLanguage, startTime);
            } else {
                System.out.println("Language " + roadmapLanguage + " not allowed");
                return new Roadmap(UUID.randomUUID(), title, RoadmapType.FRONTEND, roadmapStatus, startTime);
            }
    }

    public static Roadmap createBackEnd(String title,
                                        RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                        LocalDateTime startTime)
    {       CollectRoadmapType collectRoadmapType = new CollectRoadmapType();
            if (roadmapLanguage == null || collectRoadmapType.getBackList().contains(roadmapLanguage)) {
                return new Roadmap(UUID.randomUUID(), title, RoadmapType.BACKEND, roadmapStatus, roadmapLanguage, startTime);
            } else {
                System.out.println("Language " + roadmapLanguage + " not allowed");
                return new Roadmap(UUID.randomUUID(), title, RoadmapType.BACKEND, roadmapStatus, startTime);
            }
    }

    @Override
    public String toString() {
        return "Roadmap{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", roadmapStatus=" + roadmapStatus +
                ", roadmapLanguage=" + roadmapLanguage +
                ", startTime=" + startTime +
                ", undoneDuration=" + undoneDuration +
                '}';
    }
}