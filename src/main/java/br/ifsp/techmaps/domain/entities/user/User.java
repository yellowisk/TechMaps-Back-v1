package br.ifsp.techmaps.domain.entities.user;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Roadmap> roadmaps;
    private String github;

    public User(String name, String email, String password, Roadmap roadmap) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roadmaps = null;
    }

    public User(UUID id, String name, String email, String password, Roadmap roadmap) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roadmaps = null;
    }

    public User(UUID id, String name, String email, String password, Roadmap roadmap, String github) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.github = github;
        this.roadmaps = null;
    }

    public User() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<Roadmap> getRoadmaps() {
        return roadmaps;
    }

    public void setRoadmaps(List<Roadmap> roadmaps) {
        this.roadmaps = roadmaps;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
