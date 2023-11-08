package br.ifsp.techmaps.web.model.commit.request;

public class UpdateCommitStatusRequest {
    private boolean isStaged;

    public UpdateCommitStatusRequest(boolean isStaged) {
        this.isStaged = isStaged;
    }

    public UpdateCommitStatusRequest() {
    }

    public boolean isStaged() {
        return isStaged;
    }

    public void setStaged(boolean staged) {
        isStaged = staged;
    }
}
