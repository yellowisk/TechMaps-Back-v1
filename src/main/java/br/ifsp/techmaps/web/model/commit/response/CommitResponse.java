package br.ifsp.techmaps.web.model.comit.response;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.UUID;

public class CommitResponse {
    private UUID id;

    private UUID taskId;

    private String tag;

    private CommitState state;

    public CommitResponse(UUID id, UUID taskId, String tag, CommitState state) {
        this.id = id;
        this.taskId = taskId;
        this.tag = tag;
        this.state = state;
    }

    public static CommitResponse convertFromTaskCommit(TaskCommit taskCommit) {
        return new CommitResponse(taskCommit.getCommitId(), taskCommit.getTask().getTaskId(),
                taskCommit.getCommitTag(), taskCommit.getState());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public CommitState getState() {
        return state;
    }

    public void setState(CommitState state) {
        this.state = state;
    }
}
