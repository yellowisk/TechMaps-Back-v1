package br.ifsp.techmaps.domain.roadmap;

import java.util.UUID;

public class Roadmap {
    private UUID id;
    private String title;
    private RoadmapRole roadmapRole;
    private RoadmapSkill roadmapSkill;
    private RoadmapStatus roadmapStatus;

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapSkill roadmapSkill, RoadmapStatus roadmapStatus) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapSkill = roadmapSkill;
        this.roadmapStatus = roadmapStatus;
    }

    public Roadmap(UUID id, String title, RoadmapRole roadmapRole, RoadmapSkill roadmapSkill, RoadmapStatus roadmapStatus) {
        this.id = id;
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapSkill = roadmapSkill;
        this.roadmapStatus = roadmapStatus;
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

    public RoadmapRole getRoadmapRole() {
        return roadmapRole;
    }

    public void setRoadmapRole(RoadmapRole roadmapRole) {
        this.roadmapRole = roadmapRole;
    }

    public RoadmapSkill getRoadmapSkill() {
        return roadmapSkill;
    }

    public void setRoadmapSkill(RoadmapSkill roadmapSkill) {
        this.roadmapSkill = roadmapSkill;
    }

    public RoadmapStatus getRoadmapStatus() {
        return roadmapStatus;
    }

    public void setRoadmapStatus(RoadmapStatus roadmapStatus) {
        this.roadmapStatus = roadmapStatus;
    }
}
