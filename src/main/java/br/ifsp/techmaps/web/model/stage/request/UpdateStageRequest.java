package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class UpdateStageRequest {
    private Integer stageCommit;

    public UpdateStageRequest(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }

    public UpdateStageRequest() {
    }

    public Integer getStageCommit() {
        return stageCommit;
    }

    public void setStageCommit(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }

    public Stage convertToStage() {
        return new Stage(stageCommit);
    }
}
