package br.ifsp.techmaps.domain.entities.task;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "task_git")
public class TaskGit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String commitTag;
    private boolean used;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getCommitTag() {
        return commitTag;
    }

    public void setCommitTag(String commitTag) {
        this.commitTag = commitTag;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
