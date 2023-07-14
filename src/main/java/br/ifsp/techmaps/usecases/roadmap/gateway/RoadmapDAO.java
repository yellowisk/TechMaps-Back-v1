package br.ifsp.techmaps.usecases.roadmap.gateway;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;

import java.util.*;

public interface RoadmapDAO {

    Roadmap saveRoadmap(Roadmap roadmap);

    List<Roadmap> findAll(UUID roadmapId);

    Optional<Roadmap> findRoadmapById(UUID roadmapId);

    List<Roadmap> findAllRoadmapStagesByRoadmapId(UUID roadmapId);

    Roadmap findRoadmapStageByStageId(UUID roadmapId);

    Roadmap updateRoadmap(Roadmap roadmap);

    Boolean RoadmapExists(UUID roadmapId);
}
