package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;

public interface UserCRUD {
    User registerNewUser(User user);
}
