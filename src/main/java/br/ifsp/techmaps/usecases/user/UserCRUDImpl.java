package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.gateway.UserDAO;
import br.ifsp.techmaps.web.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;
    public UserCRUDImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public User registerNewUser(UserRequest request) {
        return userDAO.addNewUser(request.toUser());
    }
}
