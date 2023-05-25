package br.ifsp.techmaps.usecases.task.gateway;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.util.*;

public interface TaskDAO {

    Optional<Task> findTaskById(UUID taskId);
    List<Task> findAllTasks();
    Task saveNewTask(Task task);
    Task updateTask(Task task);

}
