package br.ifsp.techmaps.domain.roadmap;

public enum RoadmapSkill {
    JAVA("java"),
    PYTHON("python"),
    JAVASCRIPT("javascript"),
    REACT("react"),
    NODE("node.js");

    private String skill;

    RoadmapSkill(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
