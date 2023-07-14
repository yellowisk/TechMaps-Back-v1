package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

import java.util.*;

public class UpdateStageResponse {
    private UUID stageId;
    private StageStatus stageStatus;
    private Integer stageCommit;

    public UpdateStageResponse(UUID stageId, StageStatus stageStatus, Integer stageCommit) {
        this.stageId = stageId;
        this.stageStatus = stageStatus;
        this.stageCommit = stageCommit;
    }

    public static UpdateStageResponse createForUpdate(Stage stage) {
        return new UpdateStageResponse(
                stage.getStageId(),
                stage.getStageStatus(),
                stage.getStageCommit()
        );
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
    }

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }
}
