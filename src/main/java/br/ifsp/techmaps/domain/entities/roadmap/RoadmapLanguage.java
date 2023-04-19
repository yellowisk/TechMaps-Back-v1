package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapLanguage {
    JAVA("java"),
    PYTHON("python"),
    JAVASCRIPT("javascript");

    private String skill;

    RoadmapLanguage(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
