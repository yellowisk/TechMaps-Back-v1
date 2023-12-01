package br.ifsp.techmaps.web.model.roadmap.response;

import br.ifsp.techmaps.domain.entities.roadmap.*;

import java.sql.*;
import java.util.*;

public class RoadmapResponse {

    private UUID id;
    private String title;
    private RoadmapType type;
    private boolean isCompleted;
    private RoadmapLanguage language;
    private int color;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime; //divide by 60 to get minutes
    private Integer commits;
    private UUID dashboardId;

    public RoadmapResponse(UUID id) {
        this.id = id;
    }

    public RoadmapResponse(UUID id, String title, RoadmapType type,
                           boolean isCompleted, RoadmapLanguage language,
                           int color, Timestamp startTime, Timestamp finishTime,
                           Long totalTime, Integer commits, UUID dashboardId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.isCompleted = isCompleted;
        this.language = language;
        this.color = color;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.commits = commits;
        this.dashboardId = dashboardId;
    }

    public RoadmapResponse() {}

    public static RoadmapResponse createJustId(UUID id) {
        return new RoadmapResponse(id);
    }

    public static RoadmapResponse create(Roadmap roadmap) {
        return new RoadmapResponse(roadmap.getRoadmapId(), roadmap.getTitle(), roadmap.getType(),
                roadmap.isCompleted(), roadmap.getLanguage(), roadmap.getColor().getColorCode(),
                roadmap.getStartTime(), roadmap.getFinishTime(), roadmap.getTotalTime(),
                roadmap.getRoadmapCommits(), roadmap.getDashboardId());
    }

    public static RoadmapResponse createForDeletion(Roadmap roadmap) {
        return new RoadmapResponse(roadmap.getRoadmapId());
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

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public RoadmapLanguage getLanguage() {
        return language;
    }

    public void setLanguage(RoadmapLanguage language) {
        this.language = language;
    }

    public int getColor() {return color;}

    public void setColor(int color) {this.color = color;}

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

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }
}
