package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;

import java.util.*;

public interface TaskCRUD {

    List<Task> listAllTasks();

    Task createNewTask(CreateTaskRequest createTaskRequest);
    Task findTaskByStageIdAndTaskId(UUID stageId, UUID taskId);
    Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest);

}
