package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.*;
import java.util.*;

@Service
public class RoadmapCRUDImpl implements RoadmapCRUD {

    private final RoadmapDAO roadmapDAO;
    private final DashboardDAO dashboardDAO;

    public RoadmapCRUDImpl(RoadmapDAO roadmapDAO, DashboardDAO dashboardDAO, StageDAO stageDAO) {
        this.roadmapDAO = roadmapDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public Roadmap addNewRoadmap(UUID dashboardId, CreateRoadmapRequest request) {
        Optional<Dashboard> optionalDashboard = dashboardDAO.findDashboardById(dashboardId);

        Roadmap roadmap = Roadmap.createWithoutId(request.getTitle(), request.getType(), RoadmapStatus.UNCOMPLETE,
                request.getRoadmapLanguage(), Timestamp.valueOf(LocalDateTime.now()), null, null,
                0,
                dashboardId);

        return roadmapDAO.saveRoadmap(roadmap);
    }

    @Override
    public Roadmap findRoadmapById(UUID roadmapId) {

        Optional<Roadmap> opt = roadmapDAO.findRoadmapById(roadmapId);
        roadmapDAO.refreshRoadmap(roadmapDAO.findRoadmapById(roadmapId).get());

        if (opt.isEmpty()) {
            throw new RuntimeException("Roadmap not found");
        }

        return opt.get();
    }

    @Override
    public List<Roadmap> findRoadmapsByDashboardId(UUID dashboardId) {
        List<Roadmap> roadmaps = roadmapDAO.findAllByDashboardId(dashboardId);
        roadmaps.forEach(roadmap -> roadmapDAO.refreshRoadmap(roadmap));
        return roadmaps;
    }

    @Override
    public List<Roadmap> findCompletedRoadmapsByDashboardId(UUID dashboardId) {
        List<Roadmap> roadmaps = roadmapDAO.findAllCompletedByDashboardId(dashboardId);
        roadmaps.forEach(roadmapDAO::refreshRoadmap);
        return roadmaps;
    }

    @Override
    public Roadmap deleteRoadmapById(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        if (roadmap.getRoadmapStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new RuntimeException("Couldn't delete because the roadmap '"
                    + roadmap.getTitle() + "' is complete");
        }

        roadmapDAO.deleteRoadmapById(roadmapId);
        dashboardDAO.refreshDashboard(roadmap.getDashboardId());
        return Roadmap.getNewInstanceWithOnlyId(roadmapId);
    }
}
