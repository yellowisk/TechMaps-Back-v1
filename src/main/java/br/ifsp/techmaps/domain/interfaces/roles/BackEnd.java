package br.ifsp.techmaps.domain.interfaces.roles;

import br.ifsp.techmaps.domain.entities.stage.Stage;

public interface BackEnd {
    public void internet(Stage stage);
    public void versionControl(Stage stage);
    public void relationalDatabases(Stage stage);
    public void apis(Stage stage);
    public void caching(Stage stage);
    public void testing(Stage stage);
    public void webservers(Stage stage);
}
