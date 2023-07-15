package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;

import java.util.*;

public class UpdateStageResponse {
    private UUID stageId;
    private Integer stageCommit;

    public UpdateStageResponse(UUID stageId,Integer stageCommit) {
        this.stageId = stageId;
        this.stageCommit = stageCommit;
    }

    public UpdateStageResponse() {
    }

    public static UpdateStageResponse createForUpdate(Stage stage) {
        return new UpdateStageResponse(
                stage.getStageId(),
                stage.getStageCommit()
        );
    }

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }
}
