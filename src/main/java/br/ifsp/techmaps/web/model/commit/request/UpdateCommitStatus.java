package br.ifsp.techmaps.web.model.commit.request;

public class UpdateCommitStatus {
    private String status;

    public UpdateCommitStatus(String status) {
        this.status = status;
    }

    public UpdateCommitStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
