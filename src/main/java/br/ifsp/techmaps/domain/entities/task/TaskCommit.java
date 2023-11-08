package br.ifsp.techmaps.domain.entities.task;

import java.util.*;

public class TaskCommit {
    private UUID commitId;
    private Task task;
    private String commitTag;
    private boolean isStaged;

    public TaskCommit() {
    }

    public TaskCommit(UUID commitId, Task task, String commitTag, boolean isStaged) {
        this.commitId = commitId;
        this.task = task;
        this.commitTag = commitTag;
        this.isStaged = isStaged;
    }

    public TaskCommit(UUID commitId, String commitTag, boolean isStaged) {
        this.commitId = commitId;
        this.commitTag = commitTag;
        this.isStaged = isStaged;
    }

    public TaskCommit(UUID commitId) {
        this.commitId = commitId;
    }

    public TaskCommit(boolean isStaged) {
        this.isStaged = isStaged;
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

    public boolean isStaged() {
        return isStaged;
    }

    public void setStaged(boolean staged) {
        this.isStaged = staged;
    }

    public static String createCommitTag(Task task) {
        String tag = "[ "+task.getTaskBody().getTitle()+" | finalCommit ]";
        return tag;
    }

}