package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.domain.entities.user.User;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    private StageEnum theme;
    private StageStatus stageStatus;
    @OneToMany
    private List<Task> tasks;
    private Integer stageCommit;

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, List<Task> tasks) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.tasks = tasks;
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

    public Stage(UUID stageId) {
        this.stageId = stageId;
    }

    public Stage() {}

    //TODO: BRING TO USE CASE

    public static Task createTask(Stage stage, String title, String repository_link,
                                  Timestamp date_created, Timestamp date_finished) {
        User user = new User();
        Task task = new Task(UUID.randomUUID(), stage, title,
                repository_link, date_created, date_finished);
        if (task.getStage().getStageStatus() == StageStatus.DONE) {
            throw new IllegalArgumentException("Não é possível criar uma tarefa em uma etapa concluída");
        } else {
            if (user.getGithub() == null) {
                task.getTaskCommit().setCommitTag("");
            } else {
                task.getTaskCommit().setCommitTag(task);
            }
            return task;
        }
    }

    public int findStageCommits(List<Task> tasks) {
        ArrayList<TaskCommit> stageCommits = new ArrayList<TaskCommit>();
        for (Task task : tasks) {
            if (task.getTaskCommit().getState() == CommitState.STAGED) {
                stageCommits.add(task.getTaskCommit());
            }
        }
        int numCommits = stageCommits.size();
        return numCommits;
    }

    public static Stage createStageWithOnlyId(UUID stageId) {
        return new Stage(stageId);
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

    @Override
    public String toString() {
        return "Stage{" +
                "stageId=" + stageId +
                ", roadmap={" + roadmap.getTitle() + "-"
                + roadmap.getType() + "-"
                + roadmap.getRoadmapLanguage() + "-"
                + roadmap.getRoadmapStatus() +
                "}, theme=" + theme +
                ", stageStatus=" + stageStatus +
                ", tasks=" + tasks +
                '}';
    }
}