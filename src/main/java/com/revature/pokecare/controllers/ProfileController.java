package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.UserBioRequest;
import com.revature.pokecare.dtos.responses.Principal;
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
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private final UserService userService;
    private final TokenService tokenService;

    public ProfileController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String retrieveUserBio(@RequestBody UserBioRequest request, @RequestHeader(name = "Authorization") String token) {
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            return userService.retrieveBio(principal.getId());
        } catch (InvalidRequestException e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
            throw new InvalidRequestException();
        } catch (HttpClientErrorException e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<Object> exception(InvalidRequestException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> exception(HttpClientErrorException exception) {
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }
}
