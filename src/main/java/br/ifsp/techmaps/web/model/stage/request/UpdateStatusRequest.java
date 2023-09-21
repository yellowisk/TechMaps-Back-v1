package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class UpdateStatusRequest {
    private String status;

    public UpdateStatusRequest(String status) {
        this.status = status;
    }

    public UpdateStatusRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
