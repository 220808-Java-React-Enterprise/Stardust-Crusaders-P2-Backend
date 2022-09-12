package com.revature.pokecare.controllers;

import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import com.revature.pokecare.utils.custom_exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    public TestController() {};

    @ExceptionHandler(value = {ResourceConflictException.class, InvalidRequestException.class})
    @ResponseStatus(value = HttpStatus.CREATED)
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String test() {
        return "<h1>Hello World</h1>";
    }

}
