package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.usecases.step.StepCRUD;
import br.ifsp.techmaps.usecases.task.TaskCRUD;
import br.ifsp.techmaps.web.model.task.request.*;
import br.ifsp.techmaps.web.model.task.response.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/v1/stages/{stageId}/tasks")
@RestController
public class TaskController {

    private final TaskCRUD taskCRUD;
    private final StepCRUD stepCRUD;

    public TaskController(TaskCRUD taskCRUD, StepCRUD stepCRUD) {
        this.taskCRUD = taskCRUD;
        this.stepCRUD = stepCRUD;
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable UUID stageId,
            @PathVariable UUID taskId) {
        Task task = taskCRUD.getTaskById(stageId, taskId);

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasksByStageId(
            @PathVariable UUID stageId) {
        List<Task> tasks = taskCRUD.getTasksByStageId(stageId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(TaskResponse.createFromTask(task)));

        return ResponseEntity.ok(taskResponses);
    }

    @PostMapping
    public ResponseEntity<List<TaskResponse>> addTasks(
            @PathVariable UUID stageId,
            @RequestBody CreateTaskRequest request) {

        List<Task> tasks = taskCRUD.createNewTasks(stageId, request);
        tasks.forEach(t -> stepCRUD.generateTaskSteps(t.getTaskId()));
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(TaskResponse.createFromTask(task)));

        return ResponseEntity.ok(taskResponses);
    }

    @PatchMapping("/{taskId}/repository")
    public ResponseEntity<TaskResponse> updateTaskRepository(
            @PathVariable UUID taskId,
            @RequestBody UpdateRepositoryRequest request) {
        Task task = taskCRUD.updateTaskRepository(taskId, request);

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

    @PatchMapping("/{taskId}/date-finished")
    public ResponseEntity<TaskResponse> updateTaskDateFinished(
            @PathVariable UUID taskId,
            @RequestBody UpdateDateFinishedRequest request) {
        Task task = taskCRUD.updateTaskDateFinished(taskId, request);

        return ResponseEntity.ok(TaskResponse.createFromTask(task));
    }

}
