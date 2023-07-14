package br.ifsp.techmaps.domain.entities.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import jakarta.persistence.*;

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

    public Stage(UUID stageId, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, Integer stageCommit) {
        this.stageId = stageId;
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
        this.stageCommit = stageCommit;
    }

    public Stage(Roadmap roadmap, StageEnum theme, StageStatus stageStatus) {
        this.roadmap = roadmap;
        this.theme = theme;
        this.stageStatus = stageStatus;
    }

    public Stage(UUID stageId, StageStatus stageStatus, Integer stageCommit) {
        this.stageId = stageId;
        this.stageStatus = stageStatus;
        this.stageCommit = stageCommit;
    }

    public Stage(StageStatus stageStatus, Integer stageCommit) {
        this.stageStatus = stageStatus;
        this.stageCommit = stageCommit;
    }

    public Stage(UUID stageId) {
        this.stageId = stageId;
    }

    public static Stage createStageWithOnlyId(UUID stageId) {
        return new Stage(stageId);
    }

    public static Stage createStageWithTheme(UUID id, Roadmap roadmap, StageEnum theme, StageStatus stageStatus) {
        return new Stage(id, roadmap, theme, StageStatus.UNDONE);
    }

    public static Stage createStageWithoutTasks(UUID id, Roadmap roadmap, StageEnum theme, StageStatus stageStatus, Integer stageCommit) {
        return new Stage(id, roadmap, theme, stageStatus, stageCommit);
    }

    public Stage getNewInstanceWithId(UUID id) {
        return new Stage(id, stageStatus, stageCommit);
    }

    public Stage() {}

    //TODO: BRING TO USE CASE

//    public static Task createTask(Stage stage, String title, String description,
//                                  String repository_link, Timestamp date_created,
//                                  Timestamp date_finished) {
//        User user = new User();
//        Task task = new Task(UUID.randomUUID(), stage, title,
//                description, repository_link, date_created,
//                date_finished);
//        if (task.getStage().getStageStatus() == StageStatus.DONE) {
//            throw new IllegalArgumentException("Não é possível criar uma tarefa em uma etapa concluída");
//        } else {
//            if (user.getGithub() == null) {
//                task.getTaskCommit().setCommitTag("");
//            } else {
//                task.getTaskCommit().setCommitTag(task);
//            }
//            return task;
//        }
//    }

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

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }
}