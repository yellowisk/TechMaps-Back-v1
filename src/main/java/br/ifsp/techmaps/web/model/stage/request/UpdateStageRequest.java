package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class UpdateStageRequest {
    private StageStatus stageStatus;
    private Integer stageCommit;

    public UpdateStageRequest(StageStatus stageStatus, Integer stageCommit) {
        this.stageStatus = stageStatus;
        this.stageCommit = stageCommit;
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

    public Stage convertToStage() {
        return new Stage(stageStatus, stageCommit);
    }
}
