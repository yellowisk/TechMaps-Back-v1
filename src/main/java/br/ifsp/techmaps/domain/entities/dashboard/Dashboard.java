package br.ifsp.techmaps.domain.entities.dashboard;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.domain.entities.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID dashboardId;
    @OneToMany
    private List<Roadmap> concludedRoadmaps;
    @OneToMany
    private List<Task> concludedTasks;
    @OneToMany
    private List<TaskCommit> totalCommits;

    private int totalTime;

    public Dashboard(UUID dashboardId, List<Roadmap> concludedRoadmaps, List<Task> concludedTasks, List<TaskCommit> totalCommits, int totalTime) {
        this.dashboardId = dashboardId;
        this.concludedRoadmaps = concludedRoadmaps;
        this.concludedTasks = concludedTasks;
        this.totalCommits = totalCommits;
        this.totalTime = totalTime;
    }

    public Dashboard() {
    }

    public Dashboard(UUID dashboardId, int totalTime) {
        this.dashboardId = dashboardId;
        this.totalTime = totalTime;
    }

    public static Dashboard createWithOnlyIdAndTime(UUID dashboardId, int totalTime) {
        return new Dashboard(dashboardId, totalTime);
    }

    public int calculateTotalTime(){
        for (Roadmap concludedRoadmap : concludedRoadmaps) {
            totalTime += concludedRoadmap.calculateDuration(concludedRoadmap).toHours();
        }
        return totalTime;
    }

    public void roadmapDisplayer() {
        for (Roadmap concludedRoadmap : concludedRoadmaps) {
            concludedRoadmap.getTitle();
            concludedRoadmap.getFinishTime();
            concludedRoadmap.findRoadmapCommits();
        }
    }

    public UUID getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }

    public List<Roadmap> getConcludedRoadmaps() {
        return concludedRoadmaps;
    }

    public void setConcludedRoadmaps(List<Roadmap> concludedRoadmaps) {
        this.concludedRoadmaps = concludedRoadmaps;
    }

    public List<Task> getConcludedTasks() {
        return concludedTasks;
    }

    public void setConcludedTasks(List<Task> concludedTasks) {
        this.concludedTasks = concludedTasks;
    }

    public List<TaskCommit> getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(List<TaskCommit> totalCommits) {
        this.totalCommits = totalCommits;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

}