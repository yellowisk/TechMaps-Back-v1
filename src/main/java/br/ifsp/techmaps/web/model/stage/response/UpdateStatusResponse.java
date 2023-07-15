package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class UpdateStatusResponse {
    private StageStatus status;

    public UpdateStatusResponse(StageStatus status) {
        this.status = status;
    }

    public static UpdateStatusResponse convertFromStage(Stage stage) {
        return new UpdateStatusResponse(stage.getStageStatus());
    }

    public StageStatus getStatus() {
        return status;
    }

    public void setStatus(StageStatus status) {
        this.status = status;
    }
}
