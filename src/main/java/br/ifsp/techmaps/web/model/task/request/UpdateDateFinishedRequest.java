package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;

public class UpdateDateFinishedRequest {
    private Timestamp date_finished;

    public UpdateDateFinishedRequest(Timestamp date_finished) {
        this.date_finished = date_finished;
    }

    public Task convertToTask() {
        return new Task(date_finished);
    }

    public Timestamp getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(Timestamp date_finished) {
        this.date_finished = date_finished;
    }
}
