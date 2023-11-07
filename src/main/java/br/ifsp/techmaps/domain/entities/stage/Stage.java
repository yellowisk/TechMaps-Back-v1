package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;

import java.util.*;

public class Stage {
    private UUID stageId;
    private Roadmap roadmap;
    private StageEnum theme;
    private boolean isDone;
    private List<Task> tasks;
    private int number;
    private Integer stageCommits;

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, boolean isDone, int number, Integer stageCommits) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.isDone = isDone;
        this.number = number;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, boolean isDone, List<Task> tasks) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.isDone = isDone;
        this.tasks = tasks;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, boolean isDone, Integer stageCommits) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.isDone = isDone;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, boolean isDone) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.isDone = isDone;
    }

    public Stage(Roadmap roadmap, StageEnum theme, boolean isDone) {
        this.roadmap = roadmap;
        this.theme = theme;
        this.isDone = isDone;
    }

    public Stage(UUID stageId, boolean isDone, Integer stageCommits) {
        this.stageId = stageId;
        this.isDone = isDone;
        this.stageCommits = stageCommits;
    }

    public Stage(boolean isDone, Integer stageCommits) {
        this.isDone = isDone;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId) {
        this.stageId = stageId;
    }

    public Stage(Integer stageCommits) {
        this.stageCommits = stageCommits;
    }

    public Stage(boolean isDone) {
        this.isDone = isDone;
    }

    public static Stage createStageWithoutTasks(UUID id, Roadmap roadmap, StageEnum theme, boolean isDone, int number, Integer stageCommit) {
        return new Stage(id, roadmap, theme, isDone, number, stageCommit);
    }

    public static Stage createStageWithoutTasksAndNumber(UUID id, Roadmap roadmap, StageEnum theme, boolean isDone, Integer stageCommit) {
        return new Stage(id, roadmap, theme, isDone, stageCommit);
    }

    public Stage() {}
    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public Roadmap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(Roadmap roadmap) {
        this.roadmap = roadmap;
    }

    public StageEnum getTheme() {
        return theme;
    }

    public void setTheme(StageEnum theme) {
        this.theme = theme;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getStageCommits() {
        return stageCommits;
    }

    public void setStageCommits(Integer stageCommits) {
        this.stageCommits = stageCommits;
    }
}