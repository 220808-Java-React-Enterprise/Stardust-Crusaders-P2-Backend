package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.NewPasswordRequest;
import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.dtos.requests.UserInfoRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.User;
import com.revature.pokecare.services.TokenService;
import com.revature.pokecare.services.UserService;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import com.revature.pokecare.utils.custom_exceptions.ResourceConflictException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/signup", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String signup(@RequestBody NewUserRequest request, @RequestParam(name = "role") String role) {
        try {
            return userService.register(request, role).getUser_id();
        } catch (InvalidRequestException e) {
            e.getStackTrace();
            throw new InvalidRequestException();
        } catch (ResourceConflictException e) {
            e.getStackTrace();
            throw new ResourceConflictException();
        }
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "update_info", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateUserInfo(@RequestBody UserInfoRequest request, @RequestParam(name = "info") String info, @RequestHeader(name = "Authorization") String token) {
        Principal principal = tokenService.extractRequesterDetails(token);
        String userId = principal.getId();
        String updatedInfo = request.getRequest();

        try {
            switch(info) {
                case "username":
                    userService.updateUsername(userId, updatedInfo);
                    return "Username successfully updated";
                case "email":
                    userService.updateEmail(userId, updatedInfo);
                    return "Email successfully updated";
                case "given_name":
                    userService.updateGivenName(userId, updatedInfo);
                    return "Given name successfully updated";
                case "surname":
                    userService.updateSurname(userId, updatedInfo);
                    return "Surname successfully updated";
                default:
                    throw new HttpServerErrorException(HttpStatus.BAD_GATEWAY);
            }
        } catch (InvalidRequestException e) {
            e.getStackTrace();
            throw new InvalidRequestException();
        } catch (ResourceConflictException e) {
            e.getStackTrace();
            throw new ResourceConflictException();
        }
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "update_password", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateUserPassword(@RequestBody NewPasswordRequest request, @RequestHeader(name = "Authorization") String token) {
        Principal principal = tokenService.extractRequesterDetails(token);
        String userId = principal.getId();

        try {
            userService.matchesExistingPassword(userId,request.getOldPassword());
            userService.updatePassword(userId, request.getPassword1(), request.getPassword2());
            return "Password successfully updated";
        } catch (InvalidRequestException e) {
            e.getStackTrace();
            throw new InvalidRequestException();
        }
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<Object> exception(InvalidRequestException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceConflictException.class)
    public ResponseEntity<Object> exception(ResourceConflictException exception) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> exception(HttpClientErrorException exception) {
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = HttpServerErrorException.BadGateway.class)
    public ResponseEntity<Object> exception(HttpServerErrorException exception) {
        return new ResponseEntity<>("An error occurred, please try again later", HttpStatus.BAD_GATEWAY);
    }
}
