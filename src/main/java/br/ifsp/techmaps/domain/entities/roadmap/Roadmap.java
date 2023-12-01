package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.sql.Timestamp;
import java.util.*;

public class Roadmap {
    private UUID roadmapId;
    private String title;
    private RoadmapType type;
    private boolean isCompleted;
    private RoadmapLanguage language;
    private RoadmapColor color;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime;
    private List<Stage> stages;
    private Integer roadmapCommits;
    private UUID dashboardId;

    public Roadmap() {
    }

    public Roadmap(String title, RoadmapType type, boolean isComplete, RoadmapLanguage language, RoadmapColor color,
                   Timestamp startTime, Timestamp finishTime, Long totalTime, Integer roadmapCommits, UUID dashboardId) {
        this.title = title;
        this.type = type;
        this.isCompleted = isComplete;
        this.language = language;
        this.color = color;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.roadmapCommits = roadmapCommits;
        this.dashboardId = dashboardId;
    }

    public Roadmap(UUID roadmapId, String title, RoadmapType type, boolean isComplete,
                   RoadmapLanguage language, RoadmapColor color, Timestamp startTime,
                   Timestamp finishTime, Long totalTime, Integer roadmapCommits, UUID dashboardId) {
        this.roadmapId = roadmapId;
        this.title = title;
        this.type = type;
        this.isCompleted = isComplete;
        this.language = language;
        this.color = color;
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
                                              boolean isComplete, RoadmapLanguage language, RoadmapColor roadmapColor,
                                              Timestamp startTime, Timestamp finishTime, Long totalTime,
                                              int commitCounter, UUID dashboardId) {
        return new Roadmap(roadmapId, title, type, isComplete, language,
                roadmapColor, startTime, finishTime, totalTime, commitCounter, dashboardId);

    }

    public static Roadmap getNewInstanceWithOnlyId(UUID roadmapId) {
        return new Roadmap(roadmapId);
    }

    public static Roadmap createWithoutId(String title, RoadmapType type, boolean isComplete,
                                          RoadmapLanguage roadmapLanguage, RoadmapColor roadmapColor,
                                          Timestamp startTime, Timestamp finishTime, Long totalTime,
                                          Integer roadmapCommits, UUID dashboardId) {
        return new Roadmap(title, type, isComplete, roadmapLanguage, roadmapColor,
                startTime, finishTime, totalTime, roadmapCommits, dashboardId);
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isComplete) {
        this.isCompleted = isComplete;
    }


    public RoadmapLanguage getLanguage() {
        return language;
    }

    public void setLanguage(RoadmapLanguage language) {
        this.language = language;
    }

    public RoadmapColor getColor() {return color;}

    public void setColor(RoadmapColor color) {this.color = color;}

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