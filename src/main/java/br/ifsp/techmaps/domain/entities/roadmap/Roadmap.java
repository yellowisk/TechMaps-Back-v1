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

    private UUID id;
    @ManyToOne
    private User user;
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

    public Roadmap(UUID id, User user, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime,
                   LocalDateTime undoneDuration)
    {
        this.id = id;
        this.user = user;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.undoneDuration = undoneDuration;
    }

    public Roadmap(UUID id, User user, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, LocalDateTime startTime)
    {
        this.id = id;
        this.user = user;
        this.title = title;
        this.type = roadmapType;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
    }

    public Roadmap(UUID id, User user, String title, RoadmapType roadmapType, RoadmapStatus roadmapStatus, LocalDateTime startTime)
    {
        this.id = id;
        this.user = user;
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
    public User getUser() { return user; }
    public void setUser(User user) {
        this.user = user;
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

    //TODO: INTEGRATE createFrontend, createBackend and createAndroid
    //TODO: BRING TO USE CASE
    public static Roadmap createFrontend(String title, User user,
                                         RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                         LocalDateTime startTime)
    {
        CollectRoadmapType collectRoadmapType = new CollectRoadmapType();

        if (roadmapLanguage == null || collectRoadmapType.getFrontList().contains(roadmapLanguage)) {
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), user, title, RoadmapType.FRONTEND, roadmapStatus, roadmapLanguage, startTime);
            if (user.getRoadmaps() == null) {
                user.setRoadmaps(new ArrayList<>());
                user.getRoadmaps().add(roadmap);
            } else {
                user.getRoadmaps().add(roadmap);
            }
            return roadmap;
        } else {
            System.out.println("Language " + roadmapLanguage + " not allowed");
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), user, title, RoadmapType.FRONTEND, roadmapStatus, startTime);
            user.getRoadmaps().add(roadmap);
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
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), user, title, RoadmapType.BACKEND, roadmapStatus, roadmapLanguage, startTime);
            if (user.getRoadmaps() == null) {
                user.setRoadmaps(new ArrayList<>());
                user.getRoadmaps().add(roadmap);
            } else {
                user.getRoadmaps().add(roadmap);
            }
            return roadmap;
        } else {
            System.out.println("Language " + roadmapLanguage + " not allowed");
            Roadmap roadmap = new Roadmap(UUID.randomUUID(), user, title, RoadmapType.BACKEND, roadmapStatus, startTime);
            user.getRoadmaps().add(roadmap);
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