package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.stage.StageCRUD;
import br.ifsp.techmaps.usecases.task.TaskCRUD;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.task.request.UpdateCommitStatus;
import br.ifsp.techmaps.web.model.task.request.UpdateDateFinishedRequest;
import br.ifsp.techmaps.web.model.task.request.UpdateRepositoryRequest;
import br.ifsp.techmaps.web.model.task.response.CommitResponse;
import br.ifsp.techmaps.web.model.task.response.UpdateCommitResponse;
import br.ifsp.techmaps.web.model.task.request.CreateTaskRequest;
import br.ifsp.techmaps.web.model.task.response.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RequestMapping("api/v1/stages/{stageId}/tasks")
@RestController
public class TaskController {

    private final TaskCRUD taskCRUD;
    private final StageCRUD stageCRUD;
    private final TaskDAO taskDAO;

    public TaskController(TaskCRUD taskCRUD, StageCRUD stageCRUD, TaskDAO taskDAO) {
        this.taskCRUD = taskCRUD;
        this.stageCRUD = stageCRUD;
        this.taskDAO = taskDAO;
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable UUID stageId,
            @PathVariable UUID taskId) {
        Task task = taskCRUD.getTaskById(stageId, taskId);

        System.out.println(task.getStage());
        System.out.println(task.getStage().getStageId());
        System.out.println(task.getStage().getTheme().getTopic());

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasksByStageId(
            @PathVariable UUID stageId) {
        List<Task> tasks = taskCRUD.getTasksByStageId(stageId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(TaskResponse.createFromTask(task));
        }

        return ResponseEntity.ok(taskResponses);
    }

    @GetMapping("/commits/{commitId}")
    public ResponseEntity<CommitResponse> getCommitById(
            @PathVariable UUID commitId) {
        TaskCommit taskCommit = taskCRUD.getTaskCommitById(commitId);

        return ResponseEntity.ok(CommitResponse.convertFromTaskCommit(taskCommit));
    }

    @PostMapping
    public ResponseEntity<List<TaskResponse>> addNewTask(
            @PathVariable UUID stageId,
            @RequestBody @Valid CreateTaskRequest request) {

        List<Task> tasks = taskCRUD.createNewTasks(stageId, request);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(TaskResponse.createFromTask(task));
        }

        return ResponseEntity.ok(taskResponses);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskRepository(
            @PathVariable UUID taskId,
            @RequestBody @Valid UpdateRepositoryRequest request) {
        Task task = taskCRUD.updateTaskRepository(taskId, request);

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskDateFinished(
            @PathVariable UUID taskId) {
        Task task = taskCRUD.updateTaskDateFinished(taskId);

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

    @PutMapping("/{taskId}/commits/{commitId}")
    public ResponseEntity<UpdateCommitResponse> updateCommit(
            @PathVariable UUID taskId,
            @PathVariable UUID commitId,
            @RequestBody @Valid UpdateCommitStatus request) {
        TaskCommit taskCommit = taskCRUD.updateTaskCommit(taskId, commitId, request);

        Task task = taskDAO.findTaskById(taskCommit.getTask().getId()).get();

        stageCRUD.updateStageCommit(task.getStage().getStageId());

        if(taskCommit.getState().equals(CommitState.STAGED))
            taskCRUD.updateTaskDateFinished(taskId);

        return ResponseEntity.ok(UpdateCommitResponse.convertForUpdate(taskCommit));
    }

}
