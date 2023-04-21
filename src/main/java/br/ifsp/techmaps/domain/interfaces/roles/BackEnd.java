package br.ifsp.techmaps.domain.interfaces.roles;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;

public interface BackEnd {
    public void internet(StageEnum stageEnum);
    public void versionControl(StageEnum stageEnum);
    public void relationalDatabases(StageEnum stageEnum);
    public void noSQLDatabases(StageEnum stageEnum);
    public void apis(StageEnum stageEnum);
    public void webservers(StageEnum stageEnum);
    public void cloud(StageEnum stageEnum);
    public void docker(StageEnum stageEnum);
}
