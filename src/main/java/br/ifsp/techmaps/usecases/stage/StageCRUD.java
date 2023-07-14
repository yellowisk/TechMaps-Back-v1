package br.ifsp.techmaps.usecases.stage;


import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStageRequest;

import java.util.List;
import java.util.UUID;

public interface StageCRUD {
    Boolean StageExists(UUID stageId);
    Stage addNewStage(UUID RoadmapId, CreateStageRequest request);

    List<Stage> addStagesByRoadmapId(UUID roadmapId);

    Stage getStageById(UUID stageId);
    List<Stage> getStagesByRoadmapId(UUID roadmapId);

    Stage updateStage(UUID stageId, UpdateStageRequest request);



}
