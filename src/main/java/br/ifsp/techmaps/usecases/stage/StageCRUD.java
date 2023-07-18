package br.ifsp.techmaps.usecases.stage;


import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStatusRequest;

import java.util.*;

public interface StageCRUD {
    Stage addNewStage(UUID RoadmapId, CreateStageRequest request);
    List<Stage> addStagesByRoadmapId(UUID roadmapId);
    Stage getStageById(UUID stageId);
    List<Stage> getStagesByRoadmapId(UUID roadmapId);
    Stage updateStageCommit(UUID stageId);
    Stage updateStageStatus(UUID roadmapId, UUID stageId, UpdateStatusRequest request);

    Stage deleteStageById(UUID roadmapId, UUID stageId);

}
