package br.ifsp.techmaps.domain.entities.user;

import br.ifsp.techmaps.usecases.user.gateway.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with username " + username));
    }

    public User findUserById(UUID userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with id " + userId));
    }

    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with email " + email));
    }

}
