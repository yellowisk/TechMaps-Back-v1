package br.ifsp.techmaps.usecases.task.gateway;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.*;

public interface TaskDAO {

    Task saveNewTask(Task task);

    List<Task> findAllTasksByStageId(UUID stageId);
    Optional<Task> findTaskById(UUID taskId);
    Task updateTask(Task task);
    TaskCommit createTaskCommit(Task task);
    Optional<TaskCommit> findTaskCommitById(UUID taskCommitId);

    TaskCommit updateTaskCommmit(UUID taskCommitId);

    Boolean TaskExists(UUID taskId);

}
