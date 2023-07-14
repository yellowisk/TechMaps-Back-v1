package br.ifsp.techmaps.usecases.stage.gateway;
import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.util.*;

public interface StageDAO {
    Boolean StageExists(UUID stageId);
    Stage saveStage(Stage stage);
    Optional<Stage> findStageById(UUID stageId);
    List<Stage> findStagesByRoadmapId(UUID roadmapId);

    Stage updateStage(Stage stage);

}
