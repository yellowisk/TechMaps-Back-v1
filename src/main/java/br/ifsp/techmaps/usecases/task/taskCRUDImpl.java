package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class taskCRUDImpl implements TaskCRUD {

    private final TaskDAO taskDAO;

    public taskCRUDImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> listAllTasks() {
        return null;
    }

    @Override
    public Task createNewTask(CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public Task createNewTask(CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public Task createNewTask(CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public Task findTaskById(UUID taskId) {
        return null;
    }

    @Override
    public Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest) {
        return null;
    }
}
