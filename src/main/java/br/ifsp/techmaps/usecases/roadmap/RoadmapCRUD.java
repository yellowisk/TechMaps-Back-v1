package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import br.ifsp.techmaps.web.model.roadmap.request.UpdateColorRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public interface RoadmapCRUD {
    Roadmap addNewRoadmap(UUID projectId, CreateRoadmapRequest request);
    Roadmap findRoadmapById(UUID roadmapId);
    Roadmap createCertificate(UUID userId, UUID roadmapId) throws IOException;
    List<Roadmap> findRoadmapsByDashboardId(UUID dashboardId);
    List<Roadmap> findCompletedRoadmapsByDashboardId(UUID dashboardId);
    Roadmap updateRoadmap(UUID roadmapId, UpdateColorRequest request);
    Roadmap deleteRoadmapById(UUID roadmapId);
}
