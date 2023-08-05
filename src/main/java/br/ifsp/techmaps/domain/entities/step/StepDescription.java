package br.ifsp.techmaps.domain.entities.step;

import br.ifsp.techmaps.domain.entities.task.TaskBody;

import java.util.UUID;

public class StepDescription {
    private UUID id;
    private TaskBody info;
    private String description;
    private Integer number;

    public StepDescription(UUID id, TaskBody info, String description, Integer number) {
        this.id = id;
        this.info = info;
        this.description = description;
        this.number = number;
    }

    public static StepDescription createFull (UUID id, TaskBody info, String description, Integer number) {
        return new StepDescription(id, info, description, number);
    }

    public StepDescription() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TaskBody getInfo() {
        return info;
    }

    public void setInfo(TaskBody info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
