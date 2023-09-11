package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.usecases.dashboard.DashboardCRUD;
import br.ifsp.techmaps.web.model.dashboard.response.DashboardResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequestMapping("api/v1/dashboards/")
@RestController
public class DashboardController {

    private final DashboardCRUD dashboardCRUD;

    public DashboardController(DashboardCRUD dashboardCRUD) {
        this.dashboardCRUD = dashboardCRUD;
    }

    @GetMapping("{dashboardId}")
    public ResponseEntity<DashboardResponse> getDashboardById(
            @PathVariable UUID dashboardId) {
        Dashboard dashboard = dashboardCRUD.getDashboardById(dashboardId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<DashboardResponse> getDashboardByUserId(
            @PathVariable UUID userId) {
        Dashboard dashboard = dashboardCRUD.getDashboardByUserId(userId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

    @PostMapping("{userId}")
    public ResponseEntity<DashboardResponse> saveNewDashboard(@PathVariable UUID userId) {
        Dashboard dashboard = dashboardCRUD.saveNewDashboard(userId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

}
