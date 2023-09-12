package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stageId;
    @ManyToOne
    private Roadmap roadmap;
    private StageEnum theme;
    private StageStatus stageStatus;
    @OneToMany
    private List<Task> tasks;
    private int number;
    private Integer stageCommits;

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, int number, Integer stageCommits) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.number = number;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, List<Task> tasks) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.tasks = tasks;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, Integer stageCommits) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
    }

    public Stage(Roadmap roadmap, StageEnum theme, StageStatus stageStatus) {
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
    }

    public Stage(UUID stageId, StageStatus stageStatus, Integer stageCommits) {
        this.stageId = stageId;
        this.stageStatus = stageStatus;
        this.stageCommits = stageCommits;
    }

    public Stage(StageStatus stageStatus, Integer stageCommits) {
        this.stageStatus = stageStatus;
        this.stageCommits = stageCommits;
    }

    public Stage(UUID stageId) {
        this.stageId = stageId;
    }

    public Stage(Integer stageCommits) {
        this.stageCommits = stageCommits;
    }

    public Stage(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
    }

    public static Stage createStageWithoutTasks(UUID id, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, int number, Integer stageCommit) {
        return new Stage(id, roadmap, theme, stageStatus, number, stageCommit);
    }

    public static Stage createStageWithoutTasksAndNumber(UUID id, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, Integer stageCommit) {
        return new Stage(id, roadmap, theme, stageStatus, stageCommit);
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

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
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