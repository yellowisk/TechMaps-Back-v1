package br.ifsp.techmaps.web.model.roadmap.response;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;

import java.sql.Timestamp;

public class CreateCertificateResponse {

    private String username;
    private String github;
    private String title;
    private RoadmapType type;
    private RoadmapLanguage language;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime;
    private Integer commits;

    public CreateCertificateResponse(String username, String github, String title, RoadmapType type,
                                     RoadmapLanguage language, Timestamp startTime, Timestamp finishTime,
                                     Long totalTime, Integer commits) {
        this.username = username;
        this.github = github;
        this.title = title;
        this.type = type;
        this.language = language;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.commits = commits;
    }

    public CreateCertificateResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapType getType() {
        return type;
    }

    public void setType(RoadmapType type) {
        this.type = type;
    }

    public RoadmapLanguage getLanguage() {
        return language;
    }

    public void setLanguage(RoadmapLanguage language) {
        this.language = language;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }
}
