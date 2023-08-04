package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.StepCRUD;
import br.ifsp.techmaps.web.model.step.request.CreateStepRequest;
import br.ifsp.techmaps.web.model.step.response.StepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/tasks/{taskId}/steps")
@RestController
public class StepController {

    private final StepCRUD stepCRUD;

    public StepController(StepCRUD stepCRUD) {
        this.stepCRUD = stepCRUD;
    }

    @PostMapping("/create")
    public ResponseEntity<StepResponse> createTaskStep(
            @PathVariable UUID taskId,
            @RequestBody CreateStepRequest request) {
        Step step = stepCRUD.addTask(taskId, request);

        return ResponseEntity.ok(StepResponse.createFromStep(step));
    }

}
