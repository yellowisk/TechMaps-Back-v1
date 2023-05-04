package br.ifsp.techmaps.domain.entities.task;

public enum CommitState {
    STAGED("commit staged"),
    UNSTAGED("commit unstaged");

    private String state;

    CommitState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}