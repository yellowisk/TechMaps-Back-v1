package br.ifsp.techmaps.web.model.roadmap.response;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;

import java.sql.*;
import java.util.*;

public class RoadmapResponse {

    private UUID id;
    private String title;
    private RoadmapType type;
    private RoadmapStatus roadmapStatus;
    private RoadmapLanguage roadmapLanguage;
    private Timestamp startTime;
    private Timestamp undoneDuration;
    private Integer roadmapCommits;
    private UUID dashboardId;

    public RoadmapResponse(UUID id) {
        this.id = id;
    }

    public RoadmapResponse(UUID id, String title, RoadmapType type,
                           RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                           Timestamp startTime, Timestamp undoneDuration,
                           Integer roadmapCommits, UUID dashboardId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.roadmapStatus = roadmapStatus;
        this.roadmapLanguage = roadmapLanguage;
        this.startTime = startTime;
        this.undoneDuration = undoneDuration;
        this.roadmapCommits = roadmapCommits;
        this.dashboardId = dashboardId;
    }

    public RoadmapResponse() {
    }

    public static RoadmapResponse createJustId(UUID id) {
        return new RoadmapResponse(id);
    }

    public static RoadmapResponse create(Roadmap roadmap) {
        return new RoadmapResponse(roadmap.getRoadmapId(), roadmap.getTitle(), roadmap.getType(),
                roadmap.getRoadmapStatus(), roadmap.getRoadmapLanguage(), roadmap.getStartTime(),
                roadmap.getFinishTime(), roadmap.getRoadmapCommits(), roadmap.getDashboardId());
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getUndoneDuration() {
        return undoneDuration;
    }

    public void setUndoneDuration(Timestamp undoneDuration) {
        this.undoneDuration = undoneDuration;
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
