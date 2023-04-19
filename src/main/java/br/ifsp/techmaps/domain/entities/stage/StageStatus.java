package br.ifsp.techmaps.domain.entities.stage;

public enum StageStatus {
    DONE("passed"),
    UNDONE("not passsed");

    private String status;

    StageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

