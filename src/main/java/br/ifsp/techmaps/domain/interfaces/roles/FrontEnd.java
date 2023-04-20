package br.ifsp.techmaps.domain.interfaces.roles;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;

public interface FrontEnd {
    public void internet(StageEnum stageEnum);
    public void html(StageEnum stageEnum);
    public void css(StageEnum stageEnum);
    public void versionControl(StageEnum stageEnum);
}
