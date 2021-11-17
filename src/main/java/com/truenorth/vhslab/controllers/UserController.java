package com.truenorth.vhslab.controllers;

import com.truenorth.vhslab.exceptions.ValidationException;
import com.truenorth.vhslab.entities.User;
import com.truenorth.vhslab.models.TokenModel;
import com.truenorth.vhslab.request.RegisterUserRequest;
import com.truenorth.vhslab.request.LoginUserRequest;
import com.truenorth.vhslab.request.UpdateRoleRequest;
import com.truenorth.vhslab.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //AuthenticationManager authenticationManager;

    //JwtUtils jwtUtils;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws ValidationException {
        try {
            String jwt = userService.registerUser(registerUserRequest);
            return ResponseEntity.ok(new TokenModel(jwt, registerUserRequest.getUsername()));
        }
        catch (DataIntegrityViolationException e) {
            String message = e.getMostSpecificCause().getMessage();
            if (message.contains("Users_username_key")) {
                return ResponseEntity.badRequest().body("Non unique username.");
            }
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest) throws ValidationException {
        String jwt = userService.login(loginUserRequest);
        return ResponseEntity.ok(new TokenModel(jwt, loginUserRequest.getUsername()));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateRole(@RequestBody UpdateRoleRequest updateRoleRequest) throws ValidationException {
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(updateRoleRequest.getUsername(), updateRoleRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);*/
        userService.updateRole(updateRoleRequest);
        return ResponseEntity.ok().build();
    }

}
