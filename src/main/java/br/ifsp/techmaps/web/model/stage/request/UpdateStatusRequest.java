package br.ifsp.techmaps.web.model.stage.request;

public class UpdateStatusRequest {
    private boolean isDone;

    public UpdateStatusRequest(boolean isDone) {
        this.isDone = isDone;
    }

    public UpdateStatusRequest() {
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
