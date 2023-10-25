package br.ifsp.techmaps.usecases.commit;

import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.web.model.commit.request.UpdateCommitStatus;

import java.util.UUID;

public interface CommitCRUD  {
    TaskCommit getTaskCommitById(UUID taskCommitId);
    TaskCommit getTaskCommitByTaskId(UUID taskId);

    TaskCommit updateTaskCommit(UUID taskId, UUID taskCommitId, UpdateCommitStatus request);
}
