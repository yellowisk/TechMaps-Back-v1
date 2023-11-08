package br.ifsp.techmaps.web.model.commit.response;

import br.ifsp.techmaps.domain.entities.task.TaskCommit;

public class UpdateCommitStatusResponse {

    private boolean isStaged;

    public UpdateCommitStatusResponse(boolean state) {
        this.isStaged = state;
    }

    public static UpdateCommitStatusResponse convertForUpdate(TaskCommit taskCommit) {
        return new UpdateCommitStatusResponse(taskCommit.isStaged());
    }

    public boolean isStaged() {
        return isStaged;
    }

    public void setStaged(boolean staged) {
        isStaged = staged;
    }
}
