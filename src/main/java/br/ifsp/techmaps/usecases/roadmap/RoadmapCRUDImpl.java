package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadmapCRUDImpl implements RoadmapCRUD {

    private final RoadmapDAO roadmapDAO;
    private final DashboardDAO dashboardDAO;

    public RoadmapCRUDImpl(RoadmapDAO roadmapDAO, DashboardDAO dashboardDAO) {
        this.roadmapDAO = roadmapDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public Roadmap addNewRoadmap(UUID dashboardId, CreateRoadmapRequest request) {
        Optional<Dashboard> optionalDashboard = dashboardDAO.findDashboardById(dashboardId);

        Roadmap roadmap = Roadmap.createWithoutId(request.getTitle(), request.getType(), RoadmapStatus.UNCOMPLETE,
                request.getRoadmapLanguage(), LocalDateTime.now(), null, 0,
                dashboardId);

        return roadmapDAO.saveRoadmap(roadmap);
    }
}
