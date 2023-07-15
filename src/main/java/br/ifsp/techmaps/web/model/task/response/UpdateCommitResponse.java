package br.ifsp.techmaps.web.model.task.response;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

public class UpdateCommitResponse {

    private CommitState state;

    public UpdateCommitResponse(CommitState state) {
        this.state = state;
    }

    public static UpdateCommitResponse convertFromTaskCommit(TaskCommit taskCommit) {
        return new UpdateCommitResponse(taskCommit.getState());
    }

    public CommitState getState() {
        return state;
    }

    public void setState(CommitState state) {
        this.state = state;
    }
}
