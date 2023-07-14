package br.ifsp.techmaps.web.model.task.request;

import br.ifsp.techmaps.domain.entities.task.Task;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class CreateTaskRequest {
    @NotNull
    private String link;

    public CreateTaskRequest() {
    }

    public Task convertToTask() {
        return Task.createForStage(UUID.randomUUID(), link, Timestamp.valueOf(LocalDateTime.now()));
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
