package br.ifsp.techmaps.web.model.stage.request;

import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

public class CreateStageRequest {
    private StageEnum theme;

    public CreateStageRequest() {
    }

    public CreateStageRequest(StageEnum theme) {
        this.theme = theme;
    }

    public StageEnum getTheme() {
        return theme;
    }

    public void setTheme(StageEnum theme) {
        this.theme = theme;
    }
}
