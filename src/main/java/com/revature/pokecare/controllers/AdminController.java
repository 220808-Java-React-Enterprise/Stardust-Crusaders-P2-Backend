package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.UserIdRequest;
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

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final UserService userService;
    private final TokenService tokenService;

    public AdminController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/activate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String activateUser(@RequestBody UserIdRequest request, @RequestParam(name = "value") String value, @RequestHeader(name = "Authorization") String token) {
        Principal principal = tokenService.extractRequesterDetails(token);
        if (!principal.getRole().equals("admin")) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        } else {
            try {
                userService.setActive(request.getUser_id(), Boolean.parseBoolean(value));
                return "User active = " + value;
            } catch (InvalidRequestException e) {
                e.getStackTrace();
                System.out.println(e.getMessage());
                throw new InvalidRequestException();
            }
        }
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> exception(HttpClientErrorException exception) {
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<Object> exception(InvalidRequestException e) {
        return new ResponseEntity<>("Value not recognized", HttpStatus.BAD_REQUEST);
    }
}
