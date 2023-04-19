package br.ifsp.techmaps.domain.entities.roadmap;

import java.util.UUID;

public class Roadmap {
    private UUID roadmapId;
    private String title;
    private RoadmapRole roadmapRole;
    private RoadmapLanguage roadmapLanguage;
    private RoadmapStatus roadmapStatus;

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
    }

    public Roadmap(UUID id, String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus) {
        this.roadmapId = id;
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
    }

    public UUID getId() {
        return roadmapId;
    }

    public void setId(UUID id) {
        this.roadmapId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapRole getRoadmapRole() {
        return roadmapRole;
    }

    public void setRoadmapRole(RoadmapRole roadmapRole) {
        this.roadmapRole = roadmapRole;
    }

    public RoadmapLanguage getRoadmapLanguage() {
        return roadmapLanguage;
    }

    public void setRoadmapLanguage(RoadmapLanguage roadmapLanguage) {
        this.roadmapLanguage = roadmapLanguage;
    }

    public RoadmapStatus getRoadmapStatus() {
        return roadmapStatus;
    }

    public void setRoadmapStatus(RoadmapStatus roadmapStatus) {
        this.roadmapStatus = roadmapStatus;
    }
}
