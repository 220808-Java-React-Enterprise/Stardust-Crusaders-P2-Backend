package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.LoginRequest;
import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.User;
import com.revature.pokecare.repositories.UserRepository;
import com.revature.pokecare.utils.HashConfig;
import com.revature.pokecare.utils.custom_exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final HashConfig hash;

    public UserService(UserRepository userRepo, HashConfig hash) {
        this.userRepo = userRepo;
        this.hash = hash;
    }

    public User register(NewUserRequest request, String role) {
        User user = null;
        if (isValidUsername(request.getUsername())) {
            if (!isDuplicateUsername(request.getUsername())) {
                if (isValidPassword(request.getPassword1())) {
                    if (isSamePassword(request.getPassword1(), request.getPassword2())) {
                        if (isValidEmail(request.getEmail())) {
                            if (!isDuplicateEmail(request.getEmail())) {
                                byte[] salt = hash.generateSalt();
                                String userPass = hash.hashPassword(request.getPassword1(), salt);
                                user = new User(UUID.randomUUID().toString(),
                                        request.getUsername(),
                                        userPass,
                                        request.getGiven_name(),
                                        request.getSurname(),
                                        request.getEmail(),
                                        role,
                                        false,
                                        salt);
                                userRepo.save(user);
                            }
                        }
                    }
                }
            }
        }
        return user;
    }

    public Principal login(LoginRequest request) {
        String loginPass = hash.hashPassword(request.getPassword(), userRepo.getSalt(request.getUsername()));
        User user = userRepo.login(request.getUsername(), loginPass);
        if (user == null) throw new AuthenticationException("\nIncorrect username or password");
        return new Principal(user.getUser_id(), user.getUsername(), user.getRole());
    }



    public Optional<User> getById(String id) {
        return userRepo.findById(id);
    }

    public List<User> getAll() {
        return (List<User>) userRepo.findAll();
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9_-]{3,15}$"))
            throw new InvalidRequestException("\nUsername must be between 3-15 characters and may only contain letters, numbers, dashes, and hyphens");
        return true;
    }


    public boolean isValidEmail(String email) {
        if (!email.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidRequestException("\nPlease enter a valid email address");
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,20}$"))
            throw new InvalidRequestException("\nPassword must be 8-20 characters with at least one uppercase letter, one lowercase letter, one number, and one special character");
        return true;
    }

    public boolean isSamePassword(String password, String password2) {
        if (!password2.equals(password)) throw new InvalidRequestException("Passwords do not match!");
        return true;
    }

    public boolean isDuplicateUsername(String username) {
        if (userRepo.findUsername(username) != null) throw new ResourceConflictException("\nUsername not available!");
        return false;
    }

    public boolean isDuplicateEmail(String email) {
        if (userRepo.findEmail(email) != null) throw new ResourceConflictException("\nEmail not available!");
        return false;
    }
}
