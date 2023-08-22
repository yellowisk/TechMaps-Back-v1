package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapLanguage {
    JAVA("java", "Backend"),
    PYTHON("python", "Backend"),
    JAVASCRIPT("javascript", "Frontend"),
    KOTLIN("kotlin", "Android");

    private String skill;
    private String condition;

    RoadmapLanguage(String skill, String condition) {
        this.skill = skill;
        this.condition = condition;
    }

    public static boolean validateLanguage(RoadmapLanguage roadmapLanguage) {
        for (RoadmapLanguage language : RoadmapLanguage.values()) {
            if (language.equals(roadmapLanguage)) {
                return true;
            }
        }
        return false;
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
