package br.ifsp.techmaps.domain.roadmap;

public enum RoadmapStatus {
    DONE("concluded"),
    UNDONE("unconcluded");

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
