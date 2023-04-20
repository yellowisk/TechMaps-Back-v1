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
        LEARN_WEBSERVERS("Learn WebServers"),
        LEARN_CLOUD("Learn Cloud Systems"),
    //VERSION CONTROL
        LEARN_GIT("Learn Git"),
        LEARN_GITHUB("Learn GitHub"),
        LEARN_GITLAB("Learn GitLab"),
    //APIs
        LEARN_API("Learn API"),
        LEARN_REST("Learn REST"),
        LEARN_SOAP("Learn SOAP"),

    //PRACTICES AND DESIGNS
        LEARN_AGILE("Learn Agile Methods"),
        LEARN_DEVOPS("Learn DevOps"),
        LEARN_OOP("Learn OOP"),
        LEARN_SOLID("Learn SOLID"),
        LEARN_CLEAN_CODE("Learn Clean Code"),
        LEARN_TDD("Learn TDD"),
        LEARN_CLEAN_ARCHITECTURE("Learn Clean Architecture"),

    //SOFTWARES
        LEARN_VSCODE("Learn Visual studio Code"),
        LEARN_INTELLIJ("Learn IntelliJ"),
        LEARN_ANDROID("Learn Android Studio"),
    //Frameworks
        LEARN_ANGULAR("Learn Angular"),
        LEARN_REACT("Learn React"),
        LEARN_SPRING("Learn Spring"),

    //Databases
        LEARN_MYSQL("Learn MySQL"),
        LEARN_POSTGRES("Learn PostgresSQL"),
        LEARN_DOCKER("Learn Docker"),
        LEARN_FIREBASE("Learn Firebase");

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
