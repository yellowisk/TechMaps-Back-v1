package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapStatus {
    COMPLETE("concluded"),
    UNCOMPLETE("unconcluded");

    private String status;

    RoadmapStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
