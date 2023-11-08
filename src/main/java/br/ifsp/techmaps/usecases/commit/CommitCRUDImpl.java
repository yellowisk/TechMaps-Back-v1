package br.ifsp.techmaps.usecases.commit;

import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.commit.gateway.CommitDAO;
import br.ifsp.techmaps.web.model.commit.request.UpdateCommitStatusRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommitCRUDImpl implements CommitCRUD {
    private final CommitDAO commitDAO;

    public CommitCRUDImpl(CommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Override
    public TaskCommit getTaskCommitById(UUID taskCommitId) {
        return commitDAO.findTaskCommitById(taskCommitId)
                .orElseThrow(() -> new NullPointerException("Couldn't TaskCommit with id: " + taskCommitId));
    }

    @Override
    public TaskCommit getTaskCommitByTaskId(UUID taskId) {
        return commitDAO.findTaskCommitByTaskId(taskId)
                .orElseThrow(() -> new NullPointerException("Couldn't find TaskCommit with task id: "
                        + taskId));
    }

    @Override
    public TaskCommit updateTaskCommit(UUID taskId, UUID taskCommitId, UpdateCommitStatusRequest request) {
        TaskCommit taskCommit = commitDAO.findTaskCommitById(taskCommitId)
                .orElseThrow(() -> new NullPointerException("Couldn't find TaskCommit with id: "
                        + taskCommitId));

        taskCommit.setStaged(request.isStaged());

        commitDAO.updateTaskCommmit(taskCommit);

        return taskCommit;
    }
}
