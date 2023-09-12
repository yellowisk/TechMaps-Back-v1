package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class CreateTaskRequest {
    @NotNull
    private String repository;

    public CreateTaskRequest() {
    }

    public Task convertToTask() {
        return Task.createForStage(UUID.randomUUID(), repository, Timestamp.valueOf(LocalDateTime.now()));
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

}
