package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.request.UpdateTitleAndColorRequest;

import java.io.IOException;
import java.util.*;

public interface RoadmapCRUD {
    Roadmap addNewRoadmap(UUID projectId, CreateRoadmapRequest request);
    Roadmap findRoadmapById(UUID roadmapId);
    void createCertificate(UUID userId, UUID roadmapId) throws IOException;
    List<Roadmap> findRoadmapsByDashboardId(UUID dashboardId);
    List<Roadmap> findCompletedRoadmapsByDashboardId(UUID dashboardId);
    Roadmap updateRoadmap(UUID roadmapId, UpdateTitleAndColorRequest request);
    Roadmap completeRoadmap(UUID roadmapId);
    Roadmap deleteRoadmapById(UUID roadmapId);
}
