package br.ifsp.techmaps.usecases.user.gateway;

import br.ifsp.techmaps.domain.entities.user.User;

import java.util.*;

public interface UserDAO {
    User addNewUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
}
