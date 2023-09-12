package br.ifsp.techmaps.web.model.roadmap.request;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapColor;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;

import java.util.*;

public class CreateRoadmapRequest {
    private String title;
    private RoadmapType type;
    private RoadmapLanguage language;
    private RoadmapColor color;
    private UUID dashboardId;

    public CreateRoadmapRequest(String title, RoadmapType type,
                                RoadmapLanguage language,
                                RoadmapColor color, UUID dashboardId) {
        this.title = title;
        this.type = type;
        this.language = language;
        this.color = color;
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
        return language;
    }

    public void setRoadmapLanguage(RoadmapLanguage language) {
        this.language = language;
    }

    public RoadmapColor getRoadmapColor() {
        return color;
    }

    public void setRoadmapColor(RoadmapColor color) {
        this.color = color;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }
}
