package br.ifsp.techmaps.web.model.comit.request;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

public class UpdateCommitStatus {
    private String status;

    public UpdateCommitStatus(String status) {
        this.status = status;
    }

    public UpdateCommitStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
