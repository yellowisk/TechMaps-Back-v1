package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;

public class UpdateTaskRequest {
    private String repositoryUrl;

    public UpdateTaskRequest(String repositoryUrl, Timestamp date_finished) {
        this.repositoryUrl = repositoryUrl;
    }

    public UpdateTaskRequest() {
    }

    public Task convertToTask() {
        return new Task(repositoryUrl);
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }
}
