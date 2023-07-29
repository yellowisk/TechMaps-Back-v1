package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.web.model.user.request.UserRequest;

import java.util.UUID;

public interface UserCRUD {
    User registerNewUser(UserRequest request);

    User getById(UUID id);

    User getByEmail(String email);

    User getByUsername(String username);

    User update(UserRequest request, UUID id);

}
