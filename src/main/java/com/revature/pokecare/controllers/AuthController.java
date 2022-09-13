package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.LoginRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.services.TokenService;
import com.revature.pokecare.services.UserService;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Principal login(@RequestBody LoginRequest request, HttpServletResponse resp) {
        Principal principal = userService.login(request);
        String token = tokenService.generateToken(principal);
        resp.setHeader("Authorization", token);
        return principal;
    }

}
