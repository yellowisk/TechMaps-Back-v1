package br.ifsp.techmaps.usecases.dashboard.gateway;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;

import java.util.*;

public interface DashboardDAO {

    Dashboard saveNewDashboard(UUID dashboardId, UUID userId);

    Optional<Dashboard> findDashboardById(UUID dashboardId);

    Optional<Dashboard> findDashboardByUserId(UUID userId);

    Dashboard updateTotalTasks(UUID dashboardId, List<Task> tasks);

    Dashboard updateTotalCommits(UUID dashboardId, List<TaskCommit> commits);

    Dashboard updateTotalRoadmapsAndTotalTime(UUID dashboardId, List<Roadmap> roadmaps,
                                              Long totalTime);

    Dashboard refreshDashboard(UUID dashboardId);

}
