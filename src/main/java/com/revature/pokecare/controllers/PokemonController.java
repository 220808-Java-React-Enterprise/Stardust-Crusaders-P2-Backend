package com.revature.pokecare.controllers;


import com.revature.pokecare.dtos.requests.NewEggRequest;
import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.dtos.requests.PokemonIDRequest;
import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.services.PokemonService;
import com.revature.pokecare.services.TokenService;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import com.revature.pokecare.utils.custom_exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private final PokemonService pokemonService;
    private final TokenService tokenService;

    public PokemonController(PokemonService pokemonService, TokenService tokenService) {
        this.pokemonService = pokemonService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/save", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String savePokemon(@RequestBody NewPokemonRequest newPokemonRequest, HttpServletRequest req) {
        newPokemonRequest.setUser_id(tokenService.extractRequesterDetails(req.getHeader("user-auth")).getId());
        return pokemonService.save(newPokemonRequest).getPokemon_id();
    }

    @CrossOrigin
    @GetMapping(value = "/gethp", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int getHP(@RequestBody PokemonIDRequest pokemonIDRequest) {

        int hp = pokemonService.getHP(pokemonIDRequest.getPokemon_id());

        return hp;
    }


    @CrossOrigin
    @GetMapping(value = "/viewall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ViewPokemon> viewAllPokemon(@RequestHeader(name = "user-auth") String token) {
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

    @CrossOrigin
    @GetMapping(value = "/viewindaycare", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ViewPokemon> viewInDaycare(@RequestHeader(name = "user-auth") String token) {
        try {
            List<ViewPokemon> vp = new ArrayList<>();
            for (ViewPokemon p : pokemonService.getByUserId(tokenService.extractRequesterDetails(token).getId())) {
                if (!Objects.equals(p.getEnroll_date(), "null")) {vp.add(p);}
            }
            return vp;
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

    @CrossOrigin
    @GetMapping(value = "/viewbyid", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ViewPokemon viewByID(@RequestBody PokemonIDRequest pokemonIDRequest) {
        try {
            System.out.println(pokemonIDRequest.getPokemon_id());
            return pokemonService.findByID(pokemonIDRequest.getPokemon_id());
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

    @CrossOrigin
    @PutMapping(value = "/enrollpoke", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void enrollPoke(@RequestBody PokemonIDRequest pokemonIDRequest) {
        try {
            pokemonService.enrollPoke(pokemonIDRequest.getPokemon_id());
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

    @CrossOrigin
    @PostMapping(value = "/createegg", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createEgg(@RequestHeader(name = "user-auth") String token, @RequestBody NewEggRequest newEggRequest) {
        try {
            String user_id = tokenService.extractRequesterDetails(token).getId();
            String pokemon_id1 = newEggRequest.getPokemon_id1();
            String pokemon_id2 = newEggRequest.getPokemon_id2();
            return pokemonService.createEgg(pokemon_id1, pokemon_id2, user_id);
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

    @CrossOrigin
    @PutMapping(value = "/hatchegg", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String hatchEgg(@RequestBody PokemonIDRequest pokemonIDRequest) {
        try {
            return pokemonService.hatchEgg(pokemonIDRequest.getPokemon_id());
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
