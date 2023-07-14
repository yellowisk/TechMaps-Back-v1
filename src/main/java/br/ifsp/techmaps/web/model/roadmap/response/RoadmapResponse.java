package br.ifsp.techmaps.web.model.roadmap.response;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;

import java.time.LocalDateTime;
import java.util.*;

public class RoadmapResponse {

    private UUID id;
    private String title;
    private RoadmapType type;
    private RoadmapStatus roadmapStatus;
    private RoadmapLanguage roadmapLanguage;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;
    private Integer roadmapCommits;
    private UUID dashboardId;

    public RoadmapResponse(UUID id) {
        this.id = id;
    }

    public RoadmapResponse(UUID id, String title, RoadmapType type,
                           RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                           LocalDateTime startTime, LocalDateTime undoneDuration,
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

    public static RoadmapResponse createJustId(UUID id) {
        return new RoadmapResponse(id);
    }

    public static RoadmapResponse create(UUID id, String title, RoadmapType type,
                                         RoadmapStatus roadmapStatus, RoadmapLanguage roadmapLanguage,
                                         LocalDateTime startTime, LocalDateTime undoneDuration,
                                         Integer roadmapCommits, UUID dashboardId) {
        return new RoadmapResponse(id, title, type, roadmapStatus, roadmapLanguage,
                startTime, undoneDuration, roadmapCommits, dashboardId);
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
