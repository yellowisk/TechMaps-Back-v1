package br.ifsp.techmaps.web.model.commit.response;

import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.UUID;

public class CommitResponse {
    private UUID id;

    private UUID taskId;

    private String tag;

    private boolean isStaged;

    public CommitResponse(UUID id, UUID taskId, String tag, boolean isStaged) {
        this.id = id;
        this.taskId = taskId;
        this.tag = tag;
        this.isStaged = isStaged;
    }

    public static CommitResponse convertFromTaskCommit(TaskCommit taskCommit) {
        return new CommitResponse(taskCommit.getCommitId(), taskCommit.getTask().getTaskId(),
                taskCommit.getCommitTag(), taskCommit.isStaged());
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

    public boolean isStaged() {
        return isStaged;
    }

    public void setStaged(boolean staged) {
        isStaged = staged;
    }
}
