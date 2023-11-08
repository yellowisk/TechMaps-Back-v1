package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.StageTheme;

public class CreateStageRequest {
    private StageTheme theme;

    public CreateStageRequest() {
    }

    public CreateStageRequest(StageTheme theme) {
        this.theme = theme;
    }

    public StageTheme getTheme() {
        return theme;
    }

    public void setTheme(StageTheme theme) {
        this.theme = theme;
    }
}
