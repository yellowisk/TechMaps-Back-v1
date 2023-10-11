package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.usecases.stage.StageCRUD;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStatusRequest;
import br.ifsp.techmaps.web.model.stage.response.StageResponse;
import br.ifsp.techmaps.web.model.stage.response.UpdateStatusResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequestMapping("api/v1/roadmaps/{roadmapId}/stages")
@RestController
public class StageController {
    private final StageCRUD stageCRUD;

    public StageController(StageCRUD stageCRUD) {
        this.stageCRUD = stageCRUD;
    }

    @GetMapping("/{stageId}")
    public ResponseEntity<StageResponse> getStageById(
            @PathVariable UUID stageId) {
        Stage stage = stageCRUD.getStageById(stageId);

        return ResponseEntity.ok(StageResponse.createForStage(stage));
    }

    @GetMapping
    public ResponseEntity<List<StageResponse>> getStagesByRoadmapId(
            @PathVariable UUID roadmapId) {
        List<Stage> stages = stageCRUD.getStagesByRoadmapId(roadmapId);
        List<StageResponse> stageResponses = new ArrayList<>();

        for (Stage stage : stages) {
            stageResponses.add(StageResponse.createForStage(stage));
        }

        return ResponseEntity.ok(stageResponses);
    }

    @PostMapping
    public ResponseEntity<StageResponse> addNewStage(
            @PathVariable UUID roadmapId,
            @RequestBody CreateStageRequest request) {
        Stage stage = stageCRUD.addNewStage(roadmapId, request);

        return ResponseEntity.ok(StageResponse.createFromStage(stage));
    }

    @PostMapping("/addStages")
    public ResponseEntity<List<StageResponse>> addStagesByRoadmapId(
            @PathVariable UUID roadmapId) {
        List<Stage> stages = stageCRUD.addStagesByRoadmapId(roadmapId);
        List<StageResponse> stageResponses = new ArrayList<>();

        stages.forEach(stage -> stageResponses.add(StageResponse.createForStage(stage)));

        return ResponseEntity.ok(stageResponses);
    }

    @PutMapping("/{stageId}")
    public ResponseEntity<UpdateStatusResponse> updateStageStatus(
            @PathVariable UUID roadmapId,
            @PathVariable UUID stageId,
            @RequestBody UpdateStatusRequest request) {
        Stage stage = stageCRUD.updateStageStatus(roadmapId, stageId, request);

        return ResponseEntity.ok(UpdateStatusResponse.convertFromStage(stage));
    }

    @DeleteMapping("/{stageId}")
    public ResponseEntity<StageResponse> deleteStageById(
            @PathVariable UUID roadmapId,
            @PathVariable UUID stageId) {
        Stage stage = stageCRUD.deleteStageById(roadmapId, stageId);

        return ResponseEntity.ok(StageResponse.createFromStage(stage));
    }

}
