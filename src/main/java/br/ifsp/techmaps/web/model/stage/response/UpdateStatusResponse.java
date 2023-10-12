package br.ifsp.techmaps.web.model.stage.response;

import br.ifsp.techmaps.domain.entities.stage.Stage;

public class UpdateStatusResponse {
    private boolean isDone;

    public UpdateStatusResponse(boolean isDone) {
        this.isDone = isDone;
    }

    public static UpdateStatusResponse convertFromStage(Stage stage) {
        return new UpdateStatusResponse(stage.isDone());
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
