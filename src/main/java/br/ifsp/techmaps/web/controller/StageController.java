package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.usecases.stage.StageCRUD;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.response.StageResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequestMapping("api/v1/dashboard/{dashboardId}/roadmaps/{roadmapId}/stages")
@RestController
public class StageController {
    private final StageCRUD stageCRUD;

    public StageController(StageCRUD stageCRUD) {
        this.stageCRUD = stageCRUD;
    }

    @PostMapping
    public ResponseEntity<StageResponse> addNewStage(
            @PathVariable UUID dashboardId,
            @PathVariable UUID roadmapId,
            @RequestBody @Valid CreateStageRequest request) {
        Stage stage = stageCRUD.addNewStage(roadmapId, request);

        return ResponseEntity.ok(StageResponse.createJustId(stage.getStageId()));
    }

    @PostMapping("/addStages")
    public ResponseEntity<List<StageResponse>> addStagesByRoadmapId(
            @PathVariable UUID dashboardId,
            @PathVariable UUID roadmapId) {
        List<Stage> stages = stageCRUD.addStagesByRoadmapId(roadmapId);
        List<StageResponse> stageResponses = new ArrayList<>();

        for (Stage stage : stages) {
            stageResponses.add(StageResponse.createJustId(stage.getStageId()));
        }

        return ResponseEntity.ok(stageResponses);
    }

}
