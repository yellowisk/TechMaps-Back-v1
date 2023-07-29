package br.ifsp.techmaps.usecases.user;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.gateway.UserDAO;
import br.ifsp.techmaps.web.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Override
    public User getById(UUID id) {
        User user = userDAO.findById(id).get();
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDAO.findByEmail(email).get();
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = userDAO.findByUsername(username).get();
        return user;
    }

    @Override
    public User update(UserRequest request, UUID userId) {
        if(!userDAO.userExists(userId)) {
            throw new RuntimeException("Couldn't find user with id: " + userId);
        }

        User user = userDAO.findById(userId).get();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(request.password());
        return userDAO.update(user);
    }
}
