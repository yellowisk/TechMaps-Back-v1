package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapRole {
    BACKEND("backend"),
    FRONTEND("frontend"),
    FULLSTACK("fullstack"),
    ANDROID("android");

    private String role;

    RoadmapRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
