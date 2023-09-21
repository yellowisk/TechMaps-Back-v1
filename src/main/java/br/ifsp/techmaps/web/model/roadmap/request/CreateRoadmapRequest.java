package br.ifsp.techmaps.web.model.roadmap.request;

import java.util.*;

public class CreateRoadmapRequest {
    private String title;
    private String type;
    private String language;
    private int color;
    private UUID dashboardId;

    public CreateRoadmapRequest(String title, String type, String language, int color, UUID dashboardId) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }
}
