package br.ifsp.techmaps.domain.entities.step;

import java.util.*;

public class Step {
    private UUID id;
    private UUID taskId;
    private int order;
    private String text;
    private boolean isFinished;
    private boolean isPriority;

    public Step(UUID id, UUID taskId, int order, String text, boolean isFinished, boolean isPriority) {
        this.id = id;
        this.taskId = taskId;
        this.order = order;
        this.text = text;
        this.isFinished = isFinished;
        this.isPriority = isPriority;
    }

    public Step() {
    }

    public static Step createFull(UUID id, UUID taskId, int order,
                                  String text, boolean isFinished, boolean isPriority) {
        return new Step(id, taskId, order, text, isFinished, isPriority);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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