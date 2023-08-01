package br.ifsp.techmaps.usecases.stage.gateway;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;
import java.util.*;

public interface StageDAO {
    Boolean StageExists(UUID stageId);
    Stage saveStage(Stage stage);
    Optional<Stage> findStageById(UUID stageId);
    List<Stage> findStagesByRoadmapId(UUID roadmapId);
    List<Timestamp> findDateFinishedOfTasksByStageId(UUID stageId);
    List<CommitState> findCommitsByStageId(UUID stageId);
    Stage updateStage(Stage stage);
    Stage updateStageStatus(Stage stage);
    Stage deleteStageById(UUID stageId);
}
