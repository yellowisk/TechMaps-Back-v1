package br.ifsp.techmaps.usecases.task.gateway;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.*;

public interface TaskDAO {

    Task saveNewTask(Task task);

    List<Task> findAllTasksByStageId(UUID stageId);
    Optional<Task> findTaskById(UUID taskId);
    Optional<Task> findTasksByRoadmapId(UUID roadmapId);
    Task updateTask(Task task);
    TaskCommit createTaskCommit(Task task);

    TaskCommit updateTaskCommmit(UUID taskCommitId);

    Boolean TaskExists(UUID taskId);

}
