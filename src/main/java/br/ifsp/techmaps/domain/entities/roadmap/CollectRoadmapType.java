package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.StageEnum;

import java.util.ArrayList;
import java.util.List;

public class CollectRoadmapType {
    private List<RoadmapLanguage> frontList = new ArrayList<>();
    private List<RoadmapLanguage> backList = new ArrayList<>();

    public CollectRoadmapType() {
        for (RoadmapLanguage skill : RoadmapLanguage.values()) {
            String condition = skill.getCondition();
            if ("Frontend".equals(condition)) {
                frontList.add(skill);
            } else if ("Backend".equals(condition)) {
                backList.add(skill);
            }
        }
    }

    public List<RoadmapLanguage> getFrontList() {
        return frontList;
    }

    public void setFrontList(List<RoadmapLanguage> frontList) {
        this.frontList = frontList;
    }

    public List<RoadmapLanguage> getBackList() {
        return backList;
    }

    public void setBackList(List<RoadmapLanguage> backList) {
        this.backList = backList;
    }
}
