package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;

public class UpdateTaskRequest {
    private String repositoryUrl;
    private Timestamp date_finished;

    public UpdateTaskRequest(String repositoryUrl, Timestamp date_finished) {
        this.repositoryUrl = repositoryUrl;
        this.date_finished = date_finished;
    }

    public UpdateTaskRequest() {
    }

    public Task convertToTask() {
        return new Task(repositoryUrl, date_finished);
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public Timestamp getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(Timestamp date_finished) {
        this.date_finished = date_finished;
    }
}
