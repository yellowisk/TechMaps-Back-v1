package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;

import java.util.*;

public interface TaskCRUD {

    List<Task> listAllTasks();

    List<Task> createNewTasks(UUID stageId, CreateTaskRequest createTaskRequest);
    Task getTaskByStageIdAndTaskId(UUID stageId, UUID taskId);
    Task getTaskById(UUID TaskId);
    Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest);

}
