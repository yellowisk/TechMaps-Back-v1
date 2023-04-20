package br.ifsp.techmaps.domain.entities.roadmap;

import java.time.LocalDateTime;
import java.util.UUID;

public class Roadmap {
    private UUID id;
    private String title;
    private RoadmapRole roadmapRole;
    private RoadmapLanguage roadmapLanguage;
    private RoadmapStatus roadmapStatus;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public Roadmap(UUID id, String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getUnconcludedDuration() {
        return undoneDuration;
    }
    public void setUnconcludedDuration(RoadmapStatus roadmapStatus) {
        if (roadmapStatus.equals(RoadmapStatus.UNDONE)) {
            this.undoneDuration = LocalDateTime.now();
        } else if (roadmapStatus.equals(RoadmapStatus.DONE)) {
            this.undoneDuration = LocalDateTime.now().minusSeconds(startTime.getSecond());
        }
    }

}