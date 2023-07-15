package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.usecases.stage.StageCRUD;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStageRequest;
import br.ifsp.techmaps.web.model.stage.response.StageResponse;
import br.ifsp.techmaps.web.model.stage.response.UpdateStageResponse;
import jakarta.validation.Valid;
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

        return ResponseEntity.ok(StageResponse.createFromStage(stage));
    }

    @GetMapping
    public ResponseEntity<List<StageResponse>> getStagesByRoadmapId(
            @PathVariable UUID roadmapId) {
        List<Stage> stages = stageCRUD.getStagesByRoadmapId(roadmapId);
        List<StageResponse> stageResponses = new ArrayList<>();

        for (Stage stage : stages) {
            stageResponses.add(StageResponse.createFromStage(stage));
        }

        return ResponseEntity.ok(stageResponses);
    }

    @PostMapping
    public ResponseEntity<StageResponse> addNewStage(
            @PathVariable UUID roadmapId,
            @RequestBody @Valid CreateStageRequest request) {
        Stage stage = stageCRUD.addNewStage(roadmapId, request);

        return ResponseEntity.ok(StageResponse.createFromStage(stage));
    }

    @PostMapping("/addStages")
    public ResponseEntity<List<StageResponse>> addStagesByRoadmapId(
            @PathVariable UUID roadmapId) {
        List<Stage> stages = stageCRUD.addStagesByRoadmapId(roadmapId);
        List<StageResponse> stageResponses = new ArrayList<>();

        for (Stage stage : stages) {
            stageResponses.add(StageResponse.createFromStage(stage));
        }

        return ResponseEntity.ok(stageResponses);
    }

    //Sorta useless
    @PatchMapping("/{stageId}")
    public ResponseEntity<UpdateStageResponse> updateNumOfCommits(
            @PathVariable UUID stageId) {
        Stage stage = stageCRUD.updateStageCommit(stageId);

        return ResponseEntity.ok(UpdateStageResponse.createForUpdate(stage));
    }

}
