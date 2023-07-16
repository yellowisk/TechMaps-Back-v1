package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class UpdateStatusRequest {
    private StageStatus status;

    public UpdateStatusRequest(StageStatus status) {
        this.status = status;
    }

    public UpdateStatusRequest() {
    }

    public Stage convertToStage() {
        return new Stage(status);
    }

    public StageStatus getStatus() {
        return status;
    }

    public void setStatus(StageStatus status) {
        this.status = status;
    }
}
