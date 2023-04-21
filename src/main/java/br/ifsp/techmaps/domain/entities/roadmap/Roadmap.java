package br.ifsp.techmaps.domain.entities.roadmap;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.interfaces.roles.BackEnd;
import br.ifsp.techmaps.domain.interfaces.roles.FrontEnd;
import br.ifsp.techmaps.domain.interfaces.roles.FullStack;

import java.time.LocalDateTime;
import java.util.UUID;

public class Roadmap implements BackEnd, FrontEnd, FullStack {
    private UUID id;
    private String title;
    private RoadmapRole roadmapRole;
    private RoadmapLanguage roadmapLanguage;
    private RoadmapStatus roadmapStatus;
    private LocalDateTime startTime;
    private LocalDateTime undoneDuration;

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public Roadmap(String title, RoadmapRole roadmapRole, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public Roadmap(UUID id, String title, RoadmapRole roadmapRole, RoadmapLanguage roadmapLanguage, RoadmapStatus roadmapStatus, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.roadmapRole = roadmapRole;
        this.roadmapLanguage = roadmapLanguage;
        this.roadmapStatus = roadmapStatus;
        this.startTime = startTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapRole getRoadmapRole() {
        return roadmapRole;
    }

    public void setRoadmapRole(RoadmapRole roadmapRole) {
        this.roadmapRole = roadmapRole;
    }

    public RoadmapLanguage getRoadmapLanguage() {
        return roadmapLanguage;
    }

    public void setRoadmapLanguage(RoadmapLanguage roadmapLanguage) {
        this.roadmapLanguage = roadmapLanguage;
    }

    public RoadmapStatus getRoadmapStatus() {
        return roadmapStatus;
    }

    public void setRoadmapStatus(RoadmapStatus roadmapStatus) {
        this.roadmapStatus = roadmapStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getUnconcludedDuration() {
        return undoneDuration;
    }
    public void setUnconcludedDuration(RoadmapStatus roadmapStatus) {
        if (roadmapStatus.equals(RoadmapStatus.UNDONE)) {
            this.undoneDuration = LocalDateTime.now();
        } else if (roadmapStatus.equals(RoadmapStatus.DONE)) {
            this.undoneDuration = LocalDateTime.now().minusSeconds(startTime.getSecond());
        }
    }
    //create method that sets a stage with a software from StageEnum section
    public final void stageSoftwares() {
        Stage stageIJ = new Stage(this, StageEnum.LEARN_INTELLIJ, StageStatus.UNDONE);
        Stage stageVS = new Stage(this, StageEnum.LEARN_VSCODE, StageStatus.UNDONE);
        Stage stageAD = new Stage(this, StageEnum.LEARN_ANDROID, StageStatus.UNDONE);
    }

    public final void stagePracticesAndParadigms() {
        Stage stageOOP = new Stage(this, StageEnum.LEARN_OOP, StageStatus.UNDONE);
        Stage stageTDD = new Stage(this, StageEnum.LEARN_TDD, StageStatus.UNDONE);
        Stage stageAGILE = new Stage(this, StageEnum.LEARN_AGILE, StageStatus.UNDONE);
        Stage stageSOLID = new Stage(this, StageEnum.LEARN_SOLID, StageStatus.UNDONE);
        Stage stageDEVOPS = new Stage(this, StageEnum.LEARN_DEVOPS, StageStatus.UNDONE);
        Stage stageCLEAN_CODE = new Stage(this, StageEnum.LEARN_CLEAN_CODE, StageStatus.UNDONE);
        Stage stageCLEAN_ARCHITECTURE = new Stage(this, StageEnum.LEARN_CLEAN_ARCHITECTURE, StageStatus.UNDONE);
    }

    public final void frameworks() {
        Stage stageSPRING = new Stage(this, StageEnum.LEARN_SPRING, StageStatus.UNDONE);
        Stage stageREACT = new Stage(this, StageEnum.LEARN_REACT, StageStatus.UNDONE);
        Stage stageANGULAR = new Stage(this, StageEnum.LEARN_ANGULAR, StageStatus.UNDONE);
    }


    @Override
    public void internet(StageEnum stageEnum) {
        Stage stage = new Stage(this, stageEnum, StageStatus.UNDONE);
    }

    @Override
    public void html(StageEnum stageEnum) {

    }

    @Override
    public void css(StageEnum stageEnum) {

    }

    @Override
    public void versionControl(StageEnum stageEnum) {

    }

    @Override
    public void relationalDatabases(StageEnum stageEnum) {

    }

    @Override
    public void noSQLDatabases(StageEnum stageEnum) {

    }

    @Override
    public void apis(StageEnum stageEnum) {

    }

    @Override
    public void webservers(StageEnum stageEnum) {

    }

    @Override
    public void cloud(StageEnum stageEnum) {

    }

    @Override
    public void docker(StageEnum stageEnum) {

    }
}