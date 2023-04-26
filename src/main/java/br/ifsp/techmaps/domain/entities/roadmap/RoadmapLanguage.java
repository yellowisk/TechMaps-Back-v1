package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapLanguage {
    JAVA("java", "Backend"),
    PYTHON("python", "Backend"),
    JAVASCRIPT("javascript", "Frontend");

    private String skill;
    private String condition;

    RoadmapLanguage(String skill, String condition) {
        this.skill = skill;
        this.condition = condition;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
