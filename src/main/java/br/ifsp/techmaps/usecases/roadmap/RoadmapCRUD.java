package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;

import java.util.UUID;

public interface RoadmapCRUD {
    Roadmap addNewRoadmap(UUID projectId, CreateRoadmapRequest request);
}
