package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.usecases.roadmap.RoadmapCRUD;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.response.RoadmapResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequestMapping("api/v1/dashboard/{dashboardId}/")
@RestController
public class RoadmapController {
    private final RoadmapCRUD roadmapCRUD;

    public RoadmapController(RoadmapCRUD roadmapCRUD) {
        this.roadmapCRUD = roadmapCRUD;
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
            @RequestBody @Valid CreateRoadmapRequest createRoadmapRequest) {
        Roadmap roadmap = roadmapCRUD.addNewRoadmap(dashboardId, createRoadmapRequest);

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

    @DeleteMapping("roadmaps/{roadmapId}")
    public ResponseEntity<RoadmapResponse> deleteRoadmapById(
            @PathVariable UUID roadmapId) {
        Roadmap roadmap = roadmapCRUD.deleteRoadmapById(roadmapId);

        return ResponseEntity.ok(RoadmapResponse.create(roadmap));
    }

}
