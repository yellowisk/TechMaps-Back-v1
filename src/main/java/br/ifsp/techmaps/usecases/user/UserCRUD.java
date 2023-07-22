package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.web.model.user.request.UserRequest;

public interface UserCRUD {
    User registerNewUser(UserRequest request);
}
