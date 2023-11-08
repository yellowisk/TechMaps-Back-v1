package br.ifsp.techmaps.usecases.task;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.stage.StageTheme;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.TaskBody;
import br.ifsp.techmaps.usecases.commit.gateway.CommitDAO;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;
import br.ifsp.techmaps.web.model.task.request.UpdateDateFinishedRequest;
import br.ifsp.techmaps.web.model.task.request.UpdateRepositoryRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class taskCRUDImpl implements TaskCRUD {

    private final TaskDAO taskDAO;
    private final CommitDAO commitDAO;
    private final StageDAO stageDAO;
    private final RoadmapDAO roadmapDAO;
    private final DashboardDAO dashboardDAO;

    public taskCRUDImpl(TaskDAO taskDAO, CommitDAO commitDAO, StageDAO stageDAO,
                        RoadmapDAO roadmapDAO, DashboardDAO dashboardDAO) {
        this.taskDAO = taskDAO;
        this.commitDAO = commitDAO;
        this.stageDAO = stageDAO;
        this.roadmapDAO = roadmapDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public List<Task> createNewTasks(UUID stageId, CreateTaskRequest createTaskRequest) {
        Stage stage = stageDAO.findStageById(stageId).get();
        StageTheme topic = stage.getTheme();

        Dashboard dashboard = dashboardDAO.findDashboardById(stage.getRoadmap().getDashboardId()).get();

        List<Task> tasks = new ArrayList<>();

        Arrays.stream(TaskBody.values()).filter(taskBody -> taskBody.getTopic().equals(topic.getTopic()))
                .forEach(taskBody -> {
            Task task = createTaskRequest.convertToTask();
            task.setTaskBody(taskBody);
            task.setNumber(tasks.size() + 1);
            task.setDashboard(dashboard);
            task.setStage(stage);
            tasks.add(task);
        });

        tasks.forEach(task -> taskDAO.saveNewTask(task));
        tasks.forEach(task -> task.setTaskCommits(commitDAO.createTaskCommit(task)));

        return tasks;
    }

    @Override
    public Task getTaskById(UUID stageId, UUID taskId) {
        Boolean stageExists = stageDAO.StageExists(stageId);
        if(!stageExists) {
            throw new NullPointerException("Stage with id " + stageId + " does not exist");
        }

        Boolean taskExists = taskDAO.taskExists(taskId);
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
    public Task updateTaskRepository(UUID taskId, UpdateRepositoryRequest request) {
        Boolean taskExists = taskDAO.taskExists(taskId);
        if(!taskExists) {
            throw new NullPointerException("Task with id " + taskId + " does not exist");
        }

        Task task = request.convertToTask();
        task.setTaskId(taskId);

        taskDAO.updateRepository(task);

        return taskDAO.findTaskById(taskId).get();
    }

    @Override
    public Task updateTaskDateFinished(UUID taskId, UpdateDateFinishedRequest request) {
        Boolean taskExists = taskDAO.taskExists(taskId);
        if(!taskExists) {
            throw new NullPointerException("Task with id " + taskId + " does not exist");
        }

        Task taskToFinish = taskDAO.findTaskById(taskId).get();
        Roadmap roadmap = roadmapDAO.findRoadmapById(taskToFinish.getStage().getRoadmap().getRoadmapId()).get();

        if (roadmap.getIsCompleted()) {
            throw new IllegalStateException("Roadmap is already complete!");
        }

        if (!request.isFinished()) {
            taskToFinish.setDateFinished(null);
        } else {
            taskToFinish.setDateFinished(Timestamp.valueOf(LocalDateTime.now()));
        }

        taskDAO.updateDateFinished(taskToFinish);

        Stage stage = stageDAO.findStageById(taskToFinish.getStage().getStageId()).get();

        List<Task> allTasksFromStage = taskDAO.findAllTasksByStageId(stage.getStageId());
        List<Task> tasksFinished = new ArrayList<>();

        for (Task task : allTasksFromStage) {
            if (task.getDateFinished() != null) {
                tasksFinished.add(task);
            }
        }

        if (tasksFinished.size() == allTasksFromStage.size()) {
            stage.setIsDone(true);
            stageDAO.updateStageStatus(stage);
        }

        return taskDAO.findTaskById(taskId).get();
    }
}
