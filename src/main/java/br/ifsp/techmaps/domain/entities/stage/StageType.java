package br.ifsp.techmaps.domain.entities.stage;

import java.util.ArrayList;
import java.util.List;

public class StageType {
    private List<StageEnum> frontList = new ArrayList<>();
    private List<StageEnum> backList = new ArrayList<>();
    private List<StageEnum> generalList = new ArrayList<>();
    private List<StageEnum> androidList = new ArrayList<>();

    public StageType() {
        for (StageEnum topic : StageEnum.values()) {
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

    public List<StageEnum> getFrontList() {
        return frontList;
    }

    public void setFrontList(List<StageEnum> frontList) {
        this.frontList = frontList;
    }

    public List<StageEnum> getBackList() {
        return backList;
    }

    public void setBackList(List<StageEnum> backList) {
        this.backList = backList;
    }

    public List<StageEnum> getAndroidList() {
        return androidList;
    }

    public void setAndroidList(List<StageEnum> androidList) {
        this.androidList = androidList;
    }

    public List<StageEnum> getGeneralList() {
        return generalList;
    }

    public void setGeneralList(List<StageEnum> generalList) {
        this.generalList = generalList;
    }
}
