package br.ifsp.techmaps.domain.entities.roadmap;


import br.ifsp.techmaps.domain.entities.stage.Stage;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.*;

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
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime;
    @OneToMany
    private List<Stage> stages;
    private Integer roadmapCommits;
    private UUID dashboardId;

    public Roadmap() {
    }

    public Roadmap(String title, RoadmapType type, RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                   Timestamp startTime, Timestamp finishTime, Long totalTime, Integer roadmapCommits, UUID dashboardId) {
        this.title = title;
        this.type = type;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.roadmapCommits = roadmapCommits;
        this.dashboardId = dashboardId;
    }

    public Roadmap(UUID roadmapId, String title, RoadmapType type, RoadmapStatus roadmapStatus,
                   RoadmapLanguage roadmapLanguage, Timestamp startTime, Timestamp finishTime,
                   Long totalTime, Integer roadmapCommits, UUID dashboardId) {
        this.roadmapId = roadmapId;
        this.title = title;
        this.type = type;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.roadmapCommits = roadmapCommits;
        this.dashboardId = dashboardId;
    }

    public Roadmap(UUID roadmapId) {
        this.roadmapId = roadmapId;
    }

    public static Roadmap createWithoutStages(UUID roadmapId, String title, RoadmapType type,
                                                          RoadmapStatus status, RoadmapLanguage language,
                                                          Timestamp startTime, Timestamp finishTime, Long totalTime,
                                                          int commitCounter, UUID dashboardId) {
        return new Roadmap(roadmapId, title, type, status, language, startTime, finishTime, totalTime, commitCounter, dashboardId);

    }

    public static Roadmap getNewInstanceWithOnlyId(UUID roadmapId) {
        return new Roadmap(roadmapId);
    }

    public static Roadmap createWithoutId(String title, RoadmapType type, RoadmapStatus roadmapStatus,
                                          RoadmapLanguage roadmapLanguage, Timestamp startTime,
                                          Timestamp finishTime, Long totalTime, Integer roadmapCommits,
                                          UUID dashboardId) {
        return new Roadmap(title, type, roadmapStatus, roadmapLanguage, startTime,
                finishTime, totalTime, roadmapCommits, dashboardId);
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public Integer getRoadmapCommits() {
        return roadmapCommits;
    }

    public void setRoadmapCommits(Integer roadmapCommits) {
        this.roadmapCommits = roadmapCommits;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }
}