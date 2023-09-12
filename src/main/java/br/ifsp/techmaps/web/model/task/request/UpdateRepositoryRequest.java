package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;

import java.sql.Timestamp;

public class UpdateRepositoryRequest {
    private String repository;

    public UpdateRepositoryRequest(String repository, Timestamp date_finished) {
        this.repository = repository;
    }

    public UpdateRepositoryRequest() {
    }

    public Task convertToTask() {
        return new Task(repository);
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
}
