package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.gateway.UserDAO;
import br.ifsp.techmaps.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public UserDAOImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${queries.sql.user-dao.insert.user}")
    private String insertUserQuery;
    @Value("${queries.sql.user-dao.select.user-by-id}")
    private String selectUserByIdQuery;
    @Value("${queries.sql.user-dao.select.user-by-email}")
    private String selectUserByEmailQuery;
    @Value("${queries.sql.user-dao.select.user-by-username}")
    private String selectUserByUsernameQuery;

    @Override
    public User addNewUser(User user) {

        UUID userId = UUID.randomUUID();

        jdbcTemplate.update(insertUserQuery, rs-> {
            rs.setObject(1, userId);
            rs.setString(2, user.getUsername());
            rs.setString(3, user.getEmail());
            rs.setString(4, passwordEncoder.encode(user.getPassword()));
            rs.setBoolean(5, true);
            rs.setBoolean(6, true);
            rs.setBoolean(7, true);
            rs.setBoolean(8, true);
        });

        return user.createWithId(userId);
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByIdQuery,
                    this::mapperUserFromRs, id);

            if(Objects.isNull(user)) {
                throw new ResourceNotFoundException("Could not find user with id: " + id);
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByEmailQuery,
                    this::mapperUserFromRs, email);

            if(Objects.isNull(user)) {
                throw new ResourceNotFoundException("Could not find user with email: " + email);
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByUsernameQuery,
                    this::mapperUserFromRs, username);

            if(Objects.isNull(user)) {
                throw new ResourceNotFoundException("Could not find user with username: " + username);
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        boolean isAccountNonExpired = rs.getBoolean("is_account_non_expired");
        boolean isAccountNonLocked = rs.getBoolean("is_account_non_locked");
        boolean isCredentialsNonExpired = rs.getBoolean("is_credentials_non_expired");
        boolean isEnabled = rs.getBoolean("is_enabled");

        return User.createFull(id, username, email,
                password, isAccountNonExpired, isAccountNonLocked,
                isCredentialsNonExpired, isEnabled);
    }

}
