package br.ifsp.techmaps.web.model.roadmap.request;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapColor;

public class UpdateColorRequest {
    private String title;
    private RoadmapColor color;

    public UpdateColorRequest(String title, RoadmapColor color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapColor getColor() {
        return color;
    }

    public void setColor(RoadmapColor color) {
        this.color = color;
    }
}
