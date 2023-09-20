package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.web.exception.BadRequestException;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.request.UpdateColorRequest;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.*;
import java.util.*;

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

        if(!dashboardDAO.dashboardExists(dashboardId)) {
            throw new RuntimeException("Couldn't find dashboard with id: " + dashboardId);
        }

        Map<RoadmapType, List<RoadmapLanguage>> typeToLangsMap = new HashMap<>();
        typeToLangsMap.put(RoadmapType.BACKEND, Arrays.asList(RoadmapLanguage.JAVA, RoadmapLanguage.PYTHON));
        typeToLangsMap.put(RoadmapType.FRONTEND, Arrays.asList(RoadmapLanguage.JAVASCRIPT));
        typeToLangsMap.put(RoadmapType.ANDROID, Arrays.asList(RoadmapLanguage.KOTLIN));

        if (!typeToLangsMap.get(request.getType()).contains(request.getRoadmapLanguage())) {
            throw new RuntimeException("Roadmap type and language are incompatible.");
        }

        Roadmap roadmap = Roadmap.createWithoutId(request.getTitle(), request.getType(), RoadmapStatus.UNCOMPLETE,
                request.getRoadmapLanguage(), request.getRoadmapColor(), Timestamp.valueOf(LocalDateTime.now()), null, null,
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
    public Roadmap updateRoadmap(UUID roadmapId, UpdateColorRequest request) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();
        roadmapDAO.refreshRoadmap(roadmap);

        if (roadmap.getStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new RuntimeException("Couldn't update because the roadmap '"
                    + roadmap.getTitle() + "' is complete");
        }

        roadmap.setColor(request.getColor());
        roadmap.setTitle(request.getTitle());

        return roadmapDAO.updateRoadmapTitleAndColor(roadmap);
    }

    @Override
    public Roadmap deleteRoadmapById(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        if (roadmap.getStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new RuntimeException("Couldn't delete because the roadmap '"
                    + roadmap.getTitle() + "' is complete");
        }

        roadmapDAO.deleteRoadmapById(roadmapId);
        dashboardDAO.refreshDashboard(roadmap.getDashboardId());
        return Roadmap.getNewInstanceWithOnlyId(roadmapId);
    }
}
