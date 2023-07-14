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

    public TaskCommit(UUID commitId, String commitTag, CommitState state) {
        this.commitId = commitId;
        this.commitTag = commitTag;
        this.state = state;
    }

    public UUID getCommitId() {
        return commitId;
    }

    public void setCommitId(UUID commitId) {
        this.commitId = commitId;
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

    //TODO: BRING TO USE CASE
//    public void setCommitTag(Task task) {
//        TaskCommit taskCommit = new TaskCommit();
//        taskCommit.setCommitTag("["+task.getTitle()+"| finalCommit]");
//    }

}