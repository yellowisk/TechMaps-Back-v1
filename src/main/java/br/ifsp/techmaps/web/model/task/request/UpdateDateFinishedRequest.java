package br.ifsp.techmaps.web.model.task.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateDateFinishedRequest {
    @JsonProperty
    private Boolean isFinished;

    public UpdateDateFinishedRequest(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public UpdateDateFinishedRequest() {
    }

    public static UpdateDateFinishedRequest create(Boolean isFinished) {
        return new UpdateDateFinishedRequest(isFinished);
    }

    public Boolean isFinished() {
        return isFinished;
    }

    public void setFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }
}
