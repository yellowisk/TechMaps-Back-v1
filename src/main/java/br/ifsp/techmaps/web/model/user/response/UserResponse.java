package br.ifsp.techmaps.web.model.user.response;

import br.ifsp.techmaps.domain.entities.user.User;

import java.util.UUID;

public class UserResponse {
    UUID id;
    String email;
    String username;

    public UserResponse(UUID id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public static UserResponse createFromUser(User user) {
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
