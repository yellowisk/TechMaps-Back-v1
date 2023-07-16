package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;

public class UpdateStageCommitRequest {
    private Integer stageCommit;

    public UpdateStageCommitRequest(Integer stageCommit) {
        this.stageCommit = stageCommit;
    }

    public UpdateStageCommitRequest() {
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
