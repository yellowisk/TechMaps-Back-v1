package br.ifsp.techmaps.domain.entities.dashboard;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.*;

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

    private Timestamp totalTime;

    public Dashboard(UUID dashboardId, List<Roadmap> concludedRoadmaps, List<Task> concludedTasks, List<TaskCommit> totalCommits, Timestamp totalTime) {
        this.dashboardId = dashboardId;
        this.concludedRoadmaps = concludedRoadmaps;
        this.concludedTasks = concludedTasks;
        this.totalCommits = totalCommits;
        this.totalTime = totalTime;
    }

    public Dashboard(UUID dashboardId, Timestamp totalTime) {
        this.dashboardId = dashboardId;
        this.totalTime = totalTime;
    }

    public Dashboard(UUID dashboardId) {
        this.dashboardId = dashboardId;
    }

    public Dashboard() {
    }

    public static Dashboard createWithOnlyIdAndTime(UUID dashboardId, Timestamp totalTime) {
        return new Dashboard(dashboardId, totalTime);
    }

    public static Dashboard createWithOnlyId(UUID dashboardId) {
        return new Dashboard(dashboardId);
    }

//    public int calculateTotalTime(){
//        for (Roadmap concludedRoadmap : concludedRoadmaps) {
//            totalTime += concludedRoadmap.calculateDuration(concludedRoadmap).toHours();
//        }
//        return totalTime;
//    }

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

    public Timestamp getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Timestamp totalTime) {
        this.totalTime = totalTime;
    }

}