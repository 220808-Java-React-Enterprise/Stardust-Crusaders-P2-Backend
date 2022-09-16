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

    @Autowired
    private final TokenService tokenService;

    public AdminController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ExceptionHandler(value = {ResourceConflictException.class, InvalidRequestException.class, HttpClientErrorException.Forbidden.class})
//    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/activate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String activateUser(@RequestBody UserIdRequest request, @RequestParam(name = "value") String value, @RequestHeader(name = "Authorization") String token) {
        Principal principal = tokenService.extractRequesterDetails(token);
        if (!principal.getRole().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR: Forbidden").toString();
        } else {
            userService.setActive(request.getUser_id(), Boolean.parseBoolean(value));
            return "User active = " + value;
        }
    }
}
