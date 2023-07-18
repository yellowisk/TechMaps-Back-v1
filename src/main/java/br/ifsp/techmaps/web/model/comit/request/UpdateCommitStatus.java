package br.ifsp.techmaps.web.model.comit.request;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

public class UpdateCommitStatus {
    private CommitState status;

    public UpdateCommitStatus(CommitState status) {
        this.status = status;
    }

    public UpdateCommitStatus() {
    }

    public TaskCommit convertToTaskCommit() {
        return new TaskCommit(status);
    }

    public CommitState getStatus() {
        return status;
    }

    public void setStatus(CommitState status) {
        this.status = status;
    }
}
