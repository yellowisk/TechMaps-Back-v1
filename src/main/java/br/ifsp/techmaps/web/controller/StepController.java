package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.StepCRUD;
import br.ifsp.techmaps.web.model.step.response.StepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/v1/tasks/{taskId}/steps")
@RestController
public class StepController {

    private final StepCRUD stepCRUD;

    public StepController(StepCRUD stepCRUD) {
        this.stepCRUD = stepCRUD;
    }

    @GetMapping
    public ResponseEntity<List<StepResponse>> getTaskSteps(
            @PathVariable UUID taskId) {
        List<Step> steps = stepCRUD.getStepsByTaskId(taskId);
        List<StepResponse> stepResponses = new ArrayList<>();
        for (Step s : steps) {
            stepResponses.add(StepResponse.createFromStep(s));
        }

        return ResponseEntity.ok(stepResponses);
    }

//    @PostMapping("/create")
//    public ResponseEntity<StepResponse> createTaskStep(
//            @PathVariable UUID taskId,
//            @RequestBody CreateStepRequest request) {
//        Step step = stepCRUD.addStep(taskId, request);
//
//        return ResponseEntity.ok(StepResponse.createFromStep(step));
//    }

    @PostMapping("/generate")
    public ResponseEntity<List<StepResponse>> generateTaskSteps(
            @PathVariable UUID taskId) {
        List<Step> step = stepCRUD.generateTaskSteps(taskId);
        List<StepResponse> stepResponses = new ArrayList<>();
        for (Step s : step) {
            stepResponses.add(StepResponse.createFromStep(s));
        }

        return ResponseEntity.ok(stepResponses);
    }

    @PatchMapping("/{stepId}/priority")
    public ResponseEntity<StepResponse> updateTaskStepPriority(
            @PathVariable UUID stepId) {
        Step step = stepCRUD.prioritizeTaskStep(stepId);

        return ResponseEntity.ok(StepResponse.createFromStep(step));
    }

    @PatchMapping("/{stepId}/finish")
    public ResponseEntity<StepResponse> updateTaskStepFinishedStatus(
            @PathVariable UUID stepId) {
        Step step = stepCRUD.finishTaskStep(stepId);

        return ResponseEntity.ok(StepResponse.createFromStep(step));
    }

}
