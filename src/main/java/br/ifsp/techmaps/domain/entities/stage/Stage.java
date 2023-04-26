package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stageId;
    @ManyToOne
    private Roadmap roadmap;
    private StageEnum stageEnum;
    private StageStatus stageStatus;
    @OneToMany
    private List<Task> tasks;

    public Stage(UUID stageId, Roadmap roadmap, StageEnum stageEnum, StageStatus stageStatus, List<Task> tasks) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.stageEnum = stageEnum;
        this.stageStatus = stageStatus;
        this.tasks = tasks;
    }

    public Stage(UUID stageId, Roadmap roadmap, StageEnum stageEnum, StageStatus stageStatus) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.stageEnum = stageEnum;
        this.stageStatus = stageStatus;
    }

    public Stage(Roadmap roadmap, StageEnum stageEnum, StageStatus stageStatus) {
        this.roadmap = roadmap;
        this.stageEnum = stageEnum;
        this.stageStatus = stageStatus;
    }

    public Stage() {

    }

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

    public StageEnum getStageEnum() {
        return stageEnum;
    }

    public void setStageEnum(StageEnum stageEnum) {
        this.stageEnum = stageEnum;
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

    public void addTask(Task task) {
        if (this.stageStatus == StageStatus.DONE) {
            throw new RuntimeException("Concluded stages don't stores new tasks.");
        } else {
            this.tasks.add(task);
        }
    }



    @Override
    public String toString() {
        return "Stage{" +
                "stageId=" + stageId +
                ", roadmap={" + roadmap.getTitle() + "-"
                + roadmap.getType() + "-"
                + roadmap.getRoadmapLanguage() + "-"
                + roadmap.getRoadmapStatus() +
                "}, stageEnum=" + stageEnum +
                ", stageStatus=" + stageStatus +
                ", tasks=" + tasks +
                '}';
    }
}
