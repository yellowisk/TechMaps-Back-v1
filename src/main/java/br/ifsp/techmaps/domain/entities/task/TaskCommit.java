package br.ifsp.techmaps.domain.entities.task;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "task_commit")
public class TaskCommit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID commitId;
    @OneToOne
    private Task task;
    private String commitTag;
    private CommitState state;

    public TaskCommit() {
    }

    public TaskCommit(UUID commitId, Task task, String commitTag, CommitState state) {
        this.commitId = commitId;
        this.task = task;
        this.commitTag = commitTag;
        this.state = state;
    }

    public TaskCommit(UUID commitId, String commitTag, CommitState state) {
        this.commitId = commitId;
        this.commitTag = commitTag;
        this.state = state;
    }

    public TaskCommit(UUID commitId) {
        this.commitId = commitId;
    }

    public TaskCommit(CommitState state) {
        this.state = state;
    }

    public static TaskCommit createWithOnlyId(UUID commitId) {
        return new TaskCommit(commitId);
    }

    public UUID getCommitId() {
        return commitId;
    }

    public void setCommitId(UUID commitId) {
        this.commitId = commitId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getCommitTag() {
        return commitTag;
    }

    public void setCommitTag(String commitTag) {
        this.commitTag = commitTag;
    }

    public CommitState getState() {
        return state;
    }

    public void setState(CommitState state) {
        this.state = state;
    }

    public static String createCommitTag(Task task) {
        String tag = "[ "+task.getTaskBody().getTitle()+" | finalCommit ]";
        return tag;
    }

}