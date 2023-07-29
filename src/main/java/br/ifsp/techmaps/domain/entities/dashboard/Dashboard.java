package br.ifsp.techmaps.domain.entities.dashboard;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID dashboardId;

    private int totalRoadmaps;

    private int totalCommits;

    private int totalTasks;

    private Long totalTime;

    private UUID userId;

    public Dashboard(UUID dashboardId, int totalRoadmaps, int totalCommits,
                     int totalTasks, Long totalTime, UUID userId) {
        this.dashboardId = dashboardId;
        this.totalRoadmaps = totalRoadmaps;
        this.totalCommits = totalCommits;
        this.totalTasks = totalTasks;
        this.totalTime = totalTime;
        this.userId = userId;
    }

    public Dashboard(UUID dashboardId, Long totalTime) {
        this.dashboardId = dashboardId;
        this.totalTime = totalTime;
    }

    public Dashboard(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }

    public Dashboard() {
    }

    public static Dashboard createWithAllFields(UUID dashboardId, int totalRoadmaps, int totalCommits,
                                                int totalTasks, Long totalTime, UUID userId) {
        return new Dashboard(dashboardId, totalRoadmaps, totalCommits, totalTasks, totalTime, userId);
    }

    public static Dashboard createWithOnlyId(UUID dashboardId) {
        return new Dashboard(dashboardId);
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