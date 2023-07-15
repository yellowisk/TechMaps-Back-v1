package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;

public class UpdateRepositoryRequest {
    private String repositoryUrl;

    public UpdateRepositoryRequest(String repositoryUrl, Timestamp date_finished) {
        this.repositoryUrl = repositoryUrl;
    }

    public UpdateRepositoryRequest() {
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
