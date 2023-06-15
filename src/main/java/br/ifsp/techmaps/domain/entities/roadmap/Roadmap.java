package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.stage.StageType;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.domain.entities.user.User;
import jakarta.persistence.*;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roadmap")
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID roadmapId;
    private String title;
    private RoadmapType type;
    private RoadmapStatus roadmapStatus;
    private RoadmapLanguage roadmapLanguage;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;

    @OneToMany
    private List<Stage> stages;
    private Integer roadmapCommits;

    public Roadmap() {
    }

    public Roadmap(UUID roadmapId, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime,
                   LocalDateTime undoneDuration)
    {
        this.roadmapId = roadmapId;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.undoneDuration = undoneDuration;
    }

    public Roadmap(UUID roadmapId, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime)
    {
        this.roadmapId = roadmapId;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
    }

    public Roadmap(UUID roadmapId, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus, LocalDateTime startTime)
    {
        this.roadmapId = roadmapId;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID id) {
        this.roadmapId = roadmapId;
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

    //TODO: BRING TO USE CASE
    public void createStage(StageEnum theme, StageStatus stageStatus, LocalDateTime startTime) {
        Stage stage = new Stage(UUID.randomUUID(), this, theme, StageStatus.UNDONE);
        StageType stageType = new StageType();

        if (this.type == RoadmapType.FRONTEND)
            if (stageType.getFrontList().contains(theme) || stageType.getGeneralList().contains(theme)) {
                if (stages == null) {
                    stages = new ArrayList<>();
                    stages.add(stage);
                } else {
                    stages.add(stage);
                }
                System.out.println("Stage " + theme + " allowed");
            } else {
                System.out.println("Stage " + theme +  " not allowed");
            }
        else if (this.type == RoadmapType.BACKEND) {
            if (stageType.getBackList().contains(theme) || stageType.getGeneralList().contains(theme)) {
                if (stages == null) {
                    stages = new ArrayList<>();
                    stages.add(stage);
                } else {
                    stages.add(stage);
                }
                System.out.println("Stage " + theme + " allowed");
            } else {
                System.out.println("Stage " + theme +  " not allowed");
            }
        }
    }

    //TODO: INTEGRATE createFrontend, createBackend and createAndroid
    //TODO: BRING TO USE CASE
    public static Roadmap createFrontend(String title, User user,
                                         RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                         LocalDateTime startTime)
    {
        CollectRoadmapType collectRoadmapType = new CollectRoadmapType();

        if (roadmapLanguage == null || collectRoadmapType.getFrontList().contains(roadmapLanguage)) {
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), title, RoadmapType.FRONTEND, roadmapStatus, roadmapLanguage, startTime);
//            if (user.getRoadmaps() == null) {
//                user.setRoadmaps(new ArrayList<>());
//                user.getRoadmaps().add(roadmap);
//            } else {
//                user.getRoadmaps().add(roadmap);
//            }
            return roadmap;
        } else {
            System.out.println("Language " + roadmapLanguage + " not allowed");
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), title, RoadmapType.FRONTEND, roadmapStatus, startTime);
//            user.getRoadmaps().add(roadmap);
            return roadmap;
        }
    }

    //TODO: INTEGRATE createFrontend, createBackend and createAndroid
    //TODO: BRING TO USE CASE
    public static Roadmap createBackEnd(String title, User user,
                                        RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                        LocalDateTime startTime)
    {
        CollectRoadmapType collectRoadmapType = new CollectRoadmapType();

        if (roadmapLanguage == null || collectRoadmapType.getBackList().contains(roadmapLanguage)) {
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), title, RoadmapType.BACKEND, roadmapStatus, roadmapLanguage, startTime);
//            if (user.getRoadmaps() == null) {
//                user.setRoadmaps(new ArrayList<>());
//                user.getRoadmaps().add(roadmap);
//            } else {
//                user.getRoadmaps().add(roadmap);
//            }
            return roadmap;
        } else {
            System.out.println("Language " + roadmapLanguage + " not allowed");
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), title, RoadmapType.BACKEND, roadmapStatus, startTime);
//            user.getRoadmaps().add(roadmap);
            return roadmap;
        }
    }

    //TODO: BRING TO USE CASE
    public void concludeRoadmap() {
        Dashboard dashboard = new Dashboard();
        if (this.roadmapStatus == RoadmapStatus.UNDONE) {
            this.roadmapStatus = RoadmapStatus.DONE;
            this.undoneDuration = LocalDateTime.now();
            dashboard.getConcludedRoadmaps().add(this);
            calculateDuration(this);
        } else {
            System.out.println("Roadmap already concluded");
        }
    }

    //Stays here (for now)
    public Duration calculateDuration(Roadmap roadmap) {
        Duration duration = Duration.between(this.startTime, this.undoneDuration);
        return duration;
    }

    //Stays here (for now)
    public int findRoadmapCommits() {
        int roadmapCommits = 0;
        for (Stage stages : stages) {
            int stageCommit = stages.findStageCommits(stages.getTasks());
            roadmapCommits = roadmapCommits + stageCommit;
        }
        return roadmapCommits;
    }

    @Override
    public String toString() {
        return "Roadmap{" +
                "id=" + roadmapId +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", roadmapStatus=" + roadmapStatus +
                ", roadmapLanguage=" + roadmapLanguage +
                ", startTime=" + startTime +
                ", undoneDuration=" + undoneDuration +
                '}';
    }
}