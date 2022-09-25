package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.LoginRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.*;
import com.revature.pokecare.repositories.*;
import com.revature.pokecare.utils.*;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    private final UserRepository userRepo = mock(UserRepository.class);
    private final HashConfig hash = mock(HashConfig.class);
    private JwtConfig jwtConfig = mock(JwtConfig.class);

    @BeforeEach
    public void setup() {
        tokenService = new TokenService(jwtConfig);
        userService = new UserService(userRepo, hash);

    }

    @AfterEach
    public void shutDown() {
        tokenService = null;
        userService = null;
    }

    @Test
    public void test_generateToken_givenValidPrincipal(){
        Principal principal = new Principal();
        Assert.assertNotNull(principal);

        String token = tokenService.generateToken(principal);

        Assert.assertNotNull(token);
    }

    @Test(expected = NullPointerException.class)
    public void test_generateToken_givenNullPrincipal(){
        Principal principal = null;
        Assert.assertNull(principal);

        String token = tokenService.generateToken(principal);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_generateToken_givenInvalidPrincipal() {
        TokenService mockToken = mock(TokenService.class);
        Principal spiedP = Mockito.spy(new Principal());

        when(mockToken.generateToken(spiedP)).thenThrow(new InvalidRequestException());

        mockToken.generateToken(spiedP);
    }

    @Test
    public void test_extractDetails_givenValidToken() {
        Principal principal = new Principal();
        Assert.assertNotNull(principal);

        String token = tokenService.generateToken(principal);
        Assert.assertNotNull(token);

        Principal result = tokenService.extractRequesterDetails(token);
        Assert.assertNotNull(result);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_extractDetails_givenInvalidUser() {
        UserService mockUser = mock(UserService.class);
        LoginRequest spiedReq = Mockito.spy(new LoginRequest());

        when(mockUser.login(spiedReq)).thenThrow(new InvalidRequestException());

        mockUser.login(spiedReq);
    }

    @Test(expected = MalformedJwtException.class)
    public void test_extractDetails_givenIllegalToken() {
        TokenService mockToken = mock(TokenService.class);
        String token = "000";

        when(mockToken.extractRequesterDetails(token)).thenThrow(new MalformedJwtException("Error"));
        mockToken.extractRequesterDetails(token);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_extractDetails_givenEmptyToken() {
        TokenService mockToken = mock(TokenService.class);
        String token = "";

        when(mockToken.extractRequesterDetails(token)).thenThrow(new IllegalArgumentException());
        mockToken.extractRequesterDetails(token);
    }
}
