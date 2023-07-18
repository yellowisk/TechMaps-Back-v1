package br.ifsp.techmaps.web.model.comit.response;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.UUID;

public class CommitResponse {
    private UUID commitId;

    private UUID taskId;

    private String CommitTag;

    private CommitState state;

    public CommitResponse(UUID commitId, UUID taskId, String commitTag, CommitState state) {
        this.commitId = commitId;
        this.taskId = taskId;
        CommitTag = commitTag;
        this.state = state;
    }

    public static CommitResponse convertFromTaskCommit(TaskCommit taskCommit) {
        return new CommitResponse(taskCommit.getCommitId(), taskCommit.getTask().getId(),
                taskCommit.getCommitTag(), taskCommit.getState());
    }

    public UUID getCommitId() {
        return commitId;
    }

    public void setCommitId(UUID commitId) {
        this.commitId = commitId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getCommitTag() {
        return CommitTag;
    }

    public void setCommitTag(String commitTag) {
        CommitTag = commitTag;
    }

    public CommitState getState() {
        return state;
    }

    public void setState(CommitState state) {
        this.state = state;
    }
}
