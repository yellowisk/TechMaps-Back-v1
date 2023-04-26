package br.ifsp.techmaps.domain.entities.stage;

import java.util.ArrayList;
import java.util.List;

public enum StageEnum {
    //Langs
        LEARN_PYTHON("Learn python", "Backend"),
        LEARN_JAVA("Learn Java", "Backend"),
        LEARN_KOTLIN("Learn Kotlin", "Android"),
        LEARN_HTML("Learn HTML", "Frontend"),
        LEARN_CSS("Learn CSS", "Frontend"),
        LEARN_JS("Learn JavaScript", "Frontend"),
    //Concepts
        LEARN_INTERNET("Learn Internet", "General"),
        LEARN_DIAGRAMS("Learn Diagrams", "General"),
        LEARN_WEBSERVERS("Learn WebServers", "Backend"),
        LEARN_CLOUD("Learn Cloud Systems", "Backend"),
    //VERSION CONTROL
        LEARN_GIT("Learn Git", "General"),
        LEARN_GITHUB("Learn GitHub", "General"),
    //APIs
        LEARN_API("Learn API", "General"),
        LEARN_REST("Learn REST", "General"),
        LEARN_SOAP("Learn SOAP", "General"),

    //PRACTICES AND DESIGNS
        LEARN_AGILE("Learn Agile Methods", "General"),
        LEARN_DEVOPS("Learn DevOps", "General"),
        LEARN_OOP("Learn OOP", "General"),
        LEARN_SOLID("Learn SOLID", "General"),
        LEARN_CLEAN_CODE("Learn Clean Code", "General"),
        LEARN_TDD("Learn TDD", "General"),
        LEARN_CLEAN_ARCHITECTURE("Learn Clean Architecture", "General"),

    //SOFTWARES
        LEARN_VSCODE("Learn Visual studio Code", "General"),
        LEARN_INTELLIJ("Learn IntelliJ", "General"),
        LEARN_ANDROID("Learn Android Studio", "Android"),
    //Frameworks
        LEARN_ANGULAR("Learn Angular", "Frontend"),
        LEARN_REACT("Learn React", "Frontend"),
        LEARN_SPRING("Learn Spring", "Backend"),

    //Databases
        LEARN_MYSQL("Learn MySQL", "Backend"),
        LEARN_POSTGRES("Learn PostgresSQL", "Backend"),
        LEARN_DOCKER("Learn Docker", "Backend"),
        LEARN_FIREBASE("Learn Firebase", "Backend");

    private String topic;
    private String condition;



    StageEnum(String status, String condition) {
        this.topic = status;
        this.condition = condition;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String status) {
        this.topic = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
