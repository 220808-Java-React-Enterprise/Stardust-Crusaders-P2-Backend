package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.UserIdRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.services.PokemonService;
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

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final UserService userService;
    private final TokenService tokenService;
    private final PokemonService pokemonService;

    public AdminController(UserService userService, TokenService tokenService, PokemonService pokemonService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.pokemonService = pokemonService;
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/activate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String activateUser(@RequestBody UserIdRequest request, @RequestParam(name = "value") String value, @RequestHeader(name = "user-auth") String token) {
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

    @CrossOrigin
    @GetMapping(value = "/viewuserpokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ViewPokemon> viewAllPokemon(@RequestHeader(name = "user-auth") String token) {
        if(!tokenService.extractRequesterDetails(token).getRole().equals("admin")) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        } else {
            try {
                return pokemonService.getByUserId(tokenService.extractRequesterDetails(token).getId());
            } catch (InvalidRequestException e) {
                e.getStackTrace();
                System.out.println(e.getMessage());
                throw new InvalidRequestException();
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println(e.getMessage());
                throw new RuntimeException();
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
