package br.ifsp.techmaps.domain.entities.dashboard;

import jakarta.persistence.*;

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

    public Dashboard(UUID dashboardId, int totalRoadmaps, int totalCommits, int totalTasks, Long totalTime) {
        this.dashboardId = dashboardId;
        this.totalRoadmaps = totalRoadmaps;
        this.totalCommits = totalCommits;
        this.totalTasks = totalTasks;
        this.totalTime = totalTime;
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

    public static Dashboard createWithAllFields(UUID dashboardId, int totalRoadmaps, int totalCommits, int totalTasks, Long totalTime) {
        return new Dashboard(dashboardId, totalRoadmaps, totalCommits, totalTasks, totalTime);
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

}