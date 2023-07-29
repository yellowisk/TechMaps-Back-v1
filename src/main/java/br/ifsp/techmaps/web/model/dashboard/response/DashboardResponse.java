package br.ifsp.techmaps.web.model.dashboard.response;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;

import java.sql.Timestamp;
import java.util.UUID;

public class DashboardResponse {
    private UUID dashboardId;

    private int totalRoadmaps;

    private int totalCommits;

    private int totalTasks;

    private Long totalTime;

    private UUID userId;

    public DashboardResponse() {
    }

    public DashboardResponse(UUID dashboardId, int totalRoadmaps, int totalCommits,
                             int totalTasks, Long totalTime, UUID userId) {
        this.dashboardId = dashboardId;
        this.totalRoadmaps = totalRoadmaps;
        this.totalCommits = totalCommits;
        this.totalTasks = totalTasks;
        this.totalTime = totalTime;
        this.userId = userId;
    }

    public static DashboardResponse createFromDashboard(Dashboard dashboard) {
        return new DashboardResponse(dashboard.getDashboardId(),
                dashboard.getTotalRoadmaps(), dashboard.getTotalCommits(),
                dashboard.getTotalTasks(), dashboard.getTotalTime(),
                dashboard.getUserId());
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }

    public int getTotalRoadmaps() {
        return totalRoadmaps;
    }

    public void setTotalRoadmaps(int totalRoadmaps) {
        this.totalRoadmaps = totalRoadmaps;
    }

    public int getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(int totalCommits) {
        this.totalCommits = totalCommits;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
