package br.ifsp.techmaps.usecases.commit;

import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommitDAO {
    Optional<TaskCommit> findTaskCommitById(UUID taskCommitId);
    List<TaskCommit> commitsByStageId(UUID stageId);
}
