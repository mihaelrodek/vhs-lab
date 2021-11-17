package com.truenorth.vhslab.services;

import com.truenorth.vhslab.exceptions.ValidationException;
import com.truenorth.vhslab.entities.User;
import com.truenorth.vhslab.models.Role;
import com.truenorth.vhslab.repositories.UserRepository;
import com.truenorth.vhslab.request.RegisterUserRequest;
import com.truenorth.vhslab.request.LoginUserRequest;
import com.truenorth.vhslab.request.UpdateRoleRequest;
import com.truenorth.vhslab.responses.ErrorType;
import com.truenorth.vhslab.responses.RegistrationField;
import com.truenorth.vhslab.security.jwt.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }


    public String registerUser(RegisterUserRequest createUserRequest) throws ValidationException {
        validate(createUserRequest.getName(), createUserRequest.getUsername(), createUserRequest.getPassword());
        User user = new User();

        user.setName(createUserRequest.getName());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(encoder.encode(createUserRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        return login(new LoginUserRequest(createUserRequest.getUsername(), createUserRequest.getPassword()));

    }

    public String login(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

    private void validate(String name, String username, String password) throws ValidationException {
        if (!name.toUpperCase().matches("^[A-Za-z ]{1,20}$")) {
            throw new ValidationException("Neispravan format imena.", RegistrationField.USERNAME, ErrorType.INVALID_FORMAT);
        }
        if (!username.toUpperCase().matches("^[A-Z0-9._\\-]{6,20}$")) {
            throw new ValidationException("Neispravan format usernamea.", RegistrationField.USERNAME, ErrorType.INVALID_FORMAT);
        }
        if (password != null && !password.toUpperCase().matches("^[A-Z0-9.!_\\-$]{8,30}$")) {
            throw new ValidationException("Neispravan format passworda.", RegistrationField.PASSWORD, ErrorType.INVALID_FORMAT);
        }
    }


    public void updateRole(UpdateRoleRequest updateRoleRequest) {

    }

    public ResponseEntity<User> getUserById(Long id) {

        Optional<User> result = userRepository.findById(id);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = result.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
