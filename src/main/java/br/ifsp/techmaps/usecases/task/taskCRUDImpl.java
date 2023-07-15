package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.TaskBody;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class taskCRUDImpl implements TaskCRUD {

    private final TaskDAO taskDAO;
    private final StageDAO stageDAO;
    private final DashboardDAO dashboardDAO;

    public taskCRUDImpl(TaskDAO taskDAO, StageDAO stageDAO, DashboardDAO dashboardDAO) {
        this.taskDAO = taskDAO;
        this.stageDAO = stageDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public List<Task> createNewTasks(UUID stageId, CreateTaskRequest createTaskRequest) {
        Stage stage = stageDAO.findStageById(stageId).get();
        StageEnum topic = stage.getTheme();

        Dashboard dashboard = dashboardDAO.findDashboardById(stage.getRoadmap().getDashboardId()).get();

        List<Task> tasks = new ArrayList<>();

        for (TaskBody taskBody : TaskBody.values()) {
            if (taskBody.getTopic().equals(topic.getTopic())) {
                Task task = createTaskRequest.convertToTask();
                task.setTaskBody(taskBody);
                task.setDashboard(dashboard);
                task.setStage(stage);
                tasks.add(task);
            }
        }

        tasks.forEach(task -> taskDAO.saveNewTask(task));
        tasks.forEach(task -> taskDAO.createTaskCommit(task));
        stage.setTasks(tasks);

        return tasks;
    }

    @Override
    public Task getTaskById(UUID stageId, UUID taskId) {
        Boolean stageExists = stageDAO.StageExists(stageId);
        if(!stageExists) {
            throw new NullPointerException("Stage with id " + stageId + " does not exist");
        }

        Boolean taskExists = taskDAO.TaskExists(taskId);
        if(!taskExists) {
            throw new NullPointerException("Task with id " + taskId + " does not exist");
        }

        Optional<Task> opt = taskDAO.findTaskById(taskId);

        return opt.get();
    }

    @Override
    public List<Task> getTasksByStageId(UUID stageId) {
        Boolean stageExists = stageDAO.StageExists(stageId);
        if(!stageExists) {
            throw new NullPointerException("Stage with id " + stageId + " does not exist");
        }
        return taskDAO.findAllTasksByStageId(stageId);
    }


    @Override
    public List<Task> listAllTasks() {
        return null;
    }

    @Override
    public Task updateTask(UUID stageId, CreateTaskRequest createTaskRequest) {
        return null;
    }

    @Override
    public TaskCommit getTaskCommitById(UUID taskCommitId) {
        return null;
    }

    @Override
    public TaskCommit updateTaskCommit(UUID taskId, UUID taskCommitId) {
        if(!taskDAO.TaskExists(taskId)) {
            throw new NullPointerException("Task with id " + taskId + " does not exist");
        }

        taskDAO.updateTaskCommmit(taskCommitId);

        return taskDAO.findTaskCommitById(taskCommitId).get();
    }
}
