package br.ifsp.techmaps.domain.entities.stage;

public enum StageEnum {
    //Langs
        LEARN_PYTHON("Learn python"),
        LEARN_JAVA("Learn Java"),
        LEARN_HTML("Learn HTML"),
        LEARN_CSS("Learn CSS"),
        LEARN_JS("Learn JavaScript"),
    //Concepts
        LEARN_INTERNET("Learn Internet"),
        LEARN_WEBSERVICES("Learn WebServices"),
        LEARN_CLOUD("Learn Cloud Systems"),
    //PRACTICES
    //AGILE METHODS
    //SOFTWARES
        LEARN_VSCODE("Learn Visual studio Code"),
        LEARN_INTELLIJ("Learn IntelliJ"),
        LEARN_ANDROID("Learn Android Studio"),
    //Frameworks
        LEARN_ANGULAR("Learn Angular"),
        LEARN_REACT("Learn React"),
    //Databases
        LEARN_MYSQL("Learn MySQL"),
        LEARN_POSTGRES("Learn PostgresSQL");

    private String status;

    StageEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
