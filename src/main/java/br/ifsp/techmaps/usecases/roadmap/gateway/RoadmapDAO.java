package br.ifsp.techmaps.usecases.roadmap.gateway;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;

import java.util.*;

public interface RoadmapDAO {

    Roadmap saveRoadmap(Roadmap roadmap);

    Roadmap refreshRoadmap(Roadmap roadmap);

    List<Roadmap> findAllCompletedByDashboardId(UUID dashboardId);
    List<Roadmap> findAllByDashboardId(UUID dashboardId);
    Optional<Roadmap> findRoadmapById(UUID roadmapId);
    Roadmap updateRoadmap(Roadmap roadmap);
    Roadmap deleteRoadmapById(UUID roadmapId);
    Boolean RoadmapExists(UUID roadmapId);
}
