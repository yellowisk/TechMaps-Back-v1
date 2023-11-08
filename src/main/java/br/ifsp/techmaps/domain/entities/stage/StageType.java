package br.ifsp.techmaps.domain.entities.stage;

import java.util.ArrayList;
import java.util.List;

public class StageType {
    private List<StageTheme> frontList = new ArrayList<>();
    private List<StageTheme> backList = new ArrayList<>();
    private List<StageTheme> generalList = new ArrayList<>();
    private List<StageTheme> androidList = new ArrayList<>();

    public StageType() {
        for (StageTheme topic : StageTheme.values()) {
            String condition = topic.getCondition();
            if ("Frontend".equals(condition)) {
                frontList.add(topic);
            } else if ("Backend".equals(condition)) {
                backList.add(topic);
            } else if ("General".equals(condition)) {
                generalList.add(topic);
            }else if ("Android".equals(condition)) {
                androidList.add(topic);
            }
        }
    }

    public List<StageTheme> getFrontList() {
        return frontList;
    }

    public void setFrontList(List<StageTheme> frontList) {
        this.frontList = frontList;
    }

    public List<StageTheme> getBackList() {
        return backList;
    }

    public void setBackList(List<StageTheme> backList) {
        this.backList = backList;
    }

    public List<StageTheme> getAndroidList() {
        return androidList;
    }

    public void setAndroidList(List<StageTheme> androidList) {
        this.androidList = androidList;
    }

    public List<StageTheme> getGeneralList() {
        return generalList;
    }

    public void setGeneralList(List<StageTheme> generalList) {
        this.generalList = generalList;
    }
}
