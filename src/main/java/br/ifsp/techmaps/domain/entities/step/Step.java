package br.ifsp.techmaps.domain.entities.step;

import java.util.*;

public class Step {
    private UUID id;
    private UUID task_id;
    private Integer order;
    private String description;
    private boolean isFinished;
    private boolean isPriority;

    public Step(UUID id, UUID task_id, Integer order, String description, boolean isFinished, boolean isPriority) {
        this.id = id;
        this.task_id = task_id;
        this.order = order;
        this.description = description;
        this.isFinished = isFinished;
        this.isPriority = isPriority;
    }

    public Step() {
    }

    public static Step createFull(UUID id, UUID task_id, Integer order,
                                  String description, boolean isFinished, boolean isPriority) {
        return new Step(id, task_id, order, description, isFinished, isPriority);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTask_id() {
        return task_id;
    }

    public void setTask_id(UUID task_id) {
        this.task_id = task_id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

}
