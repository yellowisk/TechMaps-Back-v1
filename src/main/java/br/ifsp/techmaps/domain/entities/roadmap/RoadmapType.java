package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapType {
    FRONTEND("front-end"),
    BACKEND("back-end"),
    ANDROID("android");

    private String type;

    RoadmapType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
