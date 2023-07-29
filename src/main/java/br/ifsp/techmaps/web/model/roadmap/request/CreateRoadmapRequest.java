package br.ifsp.techmaps.web.model.roadmap.request;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapColor;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;

import java.util.*;

public class CreateRoadmapRequest {
    private String title;
    private RoadmapType type;
    private RoadmapLanguage roadmapLanguage;
    private RoadmapColor roadmapColor;
    private UUID dashboardId;

    public CreateRoadmapRequest(String title, RoadmapType type,
                                RoadmapLanguage roadmapLanguage,
                                RoadmapColor roadmapColor, UUID dashboardId) {
        this.title = title;
        this.type = type;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapColor = roadmapColor;
        this.dashboardId = dashboardId;
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

    public RoadmapLanguage getRoadmapLanguage() {
        return roadmapLanguage;
    }

    public void setRoadmapLanguage(RoadmapLanguage roadmapLanguage) {
        this.roadmapLanguage = roadmapLanguage;
    }

    public RoadmapColor getRoadmapColor() {
        return roadmapColor;
    }

    public void setRoadmapColor(RoadmapColor roadmapColor) {
        this.roadmapColor = roadmapColor;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }
}
