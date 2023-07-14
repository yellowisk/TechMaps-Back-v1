package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.usecases.roadmap.RoadmapCRUD;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.response.RoadmapResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequestMapping("api/v1/dashboard/{dashboardId}/roadmaps")
@RestController
public class RoadmapController {
    private final RoadmapCRUD roadmapCRUD;

    public RoadmapController(RoadmapCRUD roadmapCRUD) {
        this.roadmapCRUD = roadmapCRUD;
    }

    @GetMapping("/{roadmapId}")
    public ResponseEntity<RoadmapResponse> getRoadmapById(
            @PathVariable UUID roadmapId) {
        Roadmap roadmap = roadmapCRUD.findRoadmapById(roadmapId);

        return ResponseEntity.ok(RoadmapResponse.createJustId(roadmap.getRoadmapId()));
    }

    @PostMapping
    public ResponseEntity<RoadmapResponse> addNewRoadmap(
            @PathVariable UUID dashboardId,
            @RequestBody @Valid CreateRoadmapRequest createRoadmapRequest) {
        Roadmap roadmap = roadmapCRUD.addNewRoadmap(dashboardId, createRoadmapRequest);

        return ResponseEntity.ok(RoadmapResponse.createJustId(roadmap.getRoadmapId()));
    }

}
