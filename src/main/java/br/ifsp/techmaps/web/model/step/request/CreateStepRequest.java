package br.ifsp.techmaps.web.model.step.request;

import br.ifsp.techmaps.domain.entities.step.Step;

import java.util.UUID;

public class CreateStepRequest {
    private int number;
    private String text;
    private String link;

    public Step convertToStep(UUID taskId) {
        return Step.createForRequest(taskId, number, text, link);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
