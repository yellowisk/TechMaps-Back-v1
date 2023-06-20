package br.ifsp.techmaps.usecases.dashboard.gateway;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DashboardDAO {

    Dashboard saveNewDashboard(Dashboard dashboard);

    Optional<Dashboard> findDashboardById(UUID dashboardId);

    Dashboard updatePipeline(Dashboard dashboard);

    Dashboard deleteRoadmap(List<Roadmap> roadmaps, UUID stepId);

    Dashboard deleteRoadmap(List<Task> tasks, UUID stepId);

    Boolean pipelineExists(UUID pipelineId);

}
