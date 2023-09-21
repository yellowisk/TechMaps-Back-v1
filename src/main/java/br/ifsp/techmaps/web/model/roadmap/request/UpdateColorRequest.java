package br.ifsp.techmaps.web.model.roadmap.request;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapColor;

public class UpdateColorRequest {
    private String title;
    private int color;

    public UpdateColorRequest(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
