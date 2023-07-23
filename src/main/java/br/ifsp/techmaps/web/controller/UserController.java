package br.ifsp.techmaps.web.controller;

import br.ifsp.techmaps.domain.entities.user.User;
import br.ifsp.techmaps.usecases.user.UserCRUD;
import br.ifsp.techmaps.web.model.user.request.UserRequest;
import br.ifsp.techmaps.web.model.user.response.UserResponse;
//import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
