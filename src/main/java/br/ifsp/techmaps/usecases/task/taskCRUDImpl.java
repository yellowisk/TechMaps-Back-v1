package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class taskCRUDImpl implements TaskCRUD {

    private final TaskDAO taskDAO;
    private final StageDAO stageDAO;

    public taskCRUDImpl(TaskDAO taskDAO, StageDAO stageDAO) {
        this.taskDAO = taskDAO;
        this.stageDAO = stageDAO;
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
    public Task findTaskByStageIdAndTaskId(UUID stageId, UUID taskId) {
        Boolean stageExists = stageDAO.StageExists(stageId);
        if(!stageExists) {
            throw new NullPointerException("Stage with id " + stageId + " does not exist");
        }

        Optional<Task> opt = taskDAO.findTaskById(taskId);

        return opt.get();
    }

    @Override
    public Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest) {
        return null;
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }
}
