package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.UserCRUD;
import br.ifsp.techmaps.web.model.user.request.UserRequest;
import br.ifsp.techmaps.web.model.user.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    private final UserCRUD userCRUD;

    public UserController(UserCRUD userCRUD) {
        this.userCRUD = userCRUD;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userCRUD.registerNewUser(userRequest);

        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @GetMapping("api/v1/user/id/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId) {
        User user = userCRUD.getById(userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @PatchMapping("api/v1/user/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable UUID userId) {
        User user = userCRUD.update(userRequest, userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

}
