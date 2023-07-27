package br.ifsp.techmaps.web.model.user.request;

import br.ifsp.techmaps.domain.entities.user.User;

public record UserRequest(String username, String email, String password) {

    public UserRequest(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User toUser() {
        return User.createFromUser(email, username, password);
    }

}
