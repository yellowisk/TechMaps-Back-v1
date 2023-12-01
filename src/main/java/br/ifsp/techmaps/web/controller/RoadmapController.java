package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.usecases.roadmap.RoadmapCRUD;
import br.ifsp.techmaps.usecases.stage.StageCRUD;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.request.UpdateTitleAndColorRequest;
import br.ifsp.techmaps.web.model.roadmap.response.RoadmapResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

@RequestMapping("api/v1/dashboards/{dashboardId}/")
@RestController
public class RoadmapController {
    private final RoadmapCRUD roadmapCRUD;
    private final StageCRUD stageCRUD;

    public RoadmapController(RoadmapCRUD roadmapCRUD, StageCRUD stageCRUD) {
        this.roadmapCRUD = roadmapCRUD;
        this.stageCRUD = stageCRUD;
    }

    @GetMapping("roadmaps/{roadmapId}")
    public ResponseEntity<RoadmapResponse> getRoadmapById(
            @PathVariable UUID roadmapId) {
        Roadmap roadmap = roadmapCRUD.findRoadmapById(roadmapId);

        return ResponseEntity.ok(RoadmapResponse.create(roadmap));
    }

    @PostMapping("roadmaps")
    public ResponseEntity<RoadmapResponse> addNewRoadmap(
            @PathVariable UUID dashboardId,
            @RequestBody CreateRoadmapRequest createRoadmapRequest) {

        Roadmap roadmap = roadmapCRUD.addNewRoadmap(dashboardId, createRoadmapRequest);
        stageCRUD.addStagesByRoadmapId(roadmap.getRoadmapId());

        return ResponseEntity.ok(RoadmapResponse.create(roadmap));
    }

    @GetMapping("roadmaps")
    public ResponseEntity<List<RoadmapResponse>> listAllRoadmaps(
            @PathVariable UUID dashboardId) {
        List<Roadmap> roadmaps = roadmapCRUD.findRoadmapsByDashboardId(dashboardId);

        return ResponseEntity.ok(
                roadmaps.stream()
                        .map(RoadmapResponse::create)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    @GetMapping("roadmaps/completed")
    public ResponseEntity<List<RoadmapResponse>> listAllCompletedRoadmaps(
            @PathVariable UUID dashboardId) {
        List<Roadmap> roadmaps = roadmapCRUD.findCompletedRoadmapsByDashboardId(dashboardId);

        return ResponseEntity.ok(
                roadmaps.stream()
                        .map(RoadmapResponse::create)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    @PatchMapping("roadmaps/{roadmapId}")
    public ResponseEntity<RoadmapResponse> updateRoadmap(
            @PathVariable UUID roadmapId,
            @RequestBody UpdateTitleAndColorRequest request) {
        Roadmap roadmap = roadmapCRUD.updateRoadmap(roadmapId, request);

        return ResponseEntity.ok(RoadmapResponse.create(roadmap));
    }

    @DeleteMapping("roadmaps/{roadmapId}")
    public ResponseEntity<RoadmapResponse> deleteRoadmapById(
            @PathVariable UUID roadmapId) {
        Roadmap roadmap = roadmapCRUD.deleteRoadmapById(roadmapId);

        return ResponseEntity.ok(RoadmapResponse.createForDeletion(roadmap));
    }

    @GetMapping("roadmaps/{roadmapId}/certificate")
    public ResponseEntity<RoadmapResponse> createCertificate(
            @PathVariable UUID dashboardId,
            @PathVariable UUID roadmapId) throws IOException {
        Roadmap roadmap = roadmapCRUD.createCertificate(dashboardId, roadmapId);

        return ResponseEntity.ok(RoadmapResponse.create(roadmap));
    }

}
