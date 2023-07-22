package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.gateway.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;
    public UserCRUDImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public User registerNewUser(User user) {
        return userDAO.addNewUser(user);
    }
}
