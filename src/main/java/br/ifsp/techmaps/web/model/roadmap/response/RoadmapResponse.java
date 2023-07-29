package br.ifsp.techmaps.web.model.roadmap.response;

import br.ifsp.techmaps.domain.entities.roadmap.*;

import java.sql.*;
import java.util.*;

public class RoadmapResponse {

    private UUID id;
    private String title;
    private RoadmapType type;
    private RoadmapStatus roadmapStatus;
    private RoadmapLanguage roadmapLanguage;
    private RoadmapColor roadmapColor;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime;
    private Integer roadmapCommits;
    private UUID dashboardId;

    public RoadmapResponse(UUID id) {
        this.id = id;
    }

    public RoadmapResponse(UUID id, String title, RoadmapType type,
                           RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                           RoadmapColor roadmapColor, Timestamp startTime, Timestamp finishTime,
                           Long totalTime, Integer roadmapCommits, UUID dashboardId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapColor = roadmapColor;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.roadmapCommits = roadmapCommits;
        this.dashboardId = dashboardId;
    }

    public RoadmapResponse() {}

    public static RoadmapResponse createJustId(UUID id) {
        return new RoadmapResponse(id);
    }

    public static RoadmapResponse create(Roadmap roadmap) {
        return new RoadmapResponse(roadmap.getRoadmapId(), roadmap.getTitle(), roadmap.getType(),
                roadmap.getRoadmapStatus(), roadmap.getRoadmapLanguage(), roadmap.getRoadmapColor(),
                roadmap.getStartTime(), roadmap.getFinishTime(), roadmap.getTotalTime(),
                roadmap.getRoadmapCommits(), roadmap.getDashboardId());
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

    public RoadmapColor getRoadmapColor() {return roadmapColor;}

    public void setRoadmapColor(RoadmapColor roadmapColor) {this.roadmapColor = roadmapColor;}

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
