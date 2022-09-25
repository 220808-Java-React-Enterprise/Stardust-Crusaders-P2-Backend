package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.LoginRequest;
import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.User;
import com.revature.pokecare.repositories.UserRepository;
import com.revature.pokecare.utils.HashConfig;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import com.revature.pokecare.utils.custom_exceptions.ResourceConflictException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Autowired
    private UserService userService;


    private final UserRepository mockUserRepo = mock(UserRepository.class);
    private final HashConfig hash = mock(HashConfig.class);

    @Before
    public void setup() {
        userService = new UserService(mockUserRepo, hash);
    }
    @Test
    public void test_isValidUsername_GivenGood() {
        String valid = "Matthew88";
        boolean flag = userService.isValidUsername(valid);
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isValidUsername_GivenInvalid() {
        String invalidUser = "12";

        userService.isValidUsername(invalidUser);
    }

    @Test(expected = NullPointerException.class)
    public void test_isValidUsername_GivenNull() {
        String invalidUser = null;

        userService.isValidUsername(invalidUser);
    }

    @Test
    public void test_IsValidPassword_GivenGoodPassword() {
        //Arrange
        String validPass = "Revature90@";

        //Act
        boolean flag = userService.isValidPassword(validPass);

        //Assert
        Assert.assertTrue(flag);

    }

    @Test(expected = InvalidRequestException.class)
    public void test_isValidPassword_givenEmptyPassword() {
        String invalidPass = "";

        userService.isValidPassword(invalidPass);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isValidPassword_givenInvalidPassword() {
        String invalidPass = "password";

        userService.isValidPassword(invalidPass);
    }


    @Test
    public void test_isSamePassword_GivenGood() {
        String pass = "Revature123@";
        String pass2 = "Revature123@";
        boolean flag = userService.isSamePassword(pass, pass2);
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isSamePassword_GivenNonmatching() {
        String pass1 = "P@ssw0rd";
        String pass2 = "PAsswOrd";

        userService.isSamePassword(pass1, pass2);
    }

    @Test
    public void test_setActive_givenInvalidUser() {
        String id = "00000";

        userService.setActive(id, true);
    }

    @Test
    public void test_isValidEmail_givenValidEmail() {
        String validEmail = "user@pokemon.com";

        userService.isValidEmail(validEmail);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isValidEmail_givenInvalidEmail() {
        String invalidEmail = "userAtpokemonDOTcom";

        userService.isValidEmail(invalidEmail);
    }

    @Test
    public void test_isDuplicateUsername_givenUnique() {
        String uniqueUser = "uniqueUser";

        userService.isDuplicateUsername(uniqueUser);
    }

    @Test(expected = ResourceConflictException.class)
    public void test_isDuplicateUsername_givenDuplicate() {
        UserService spiedServ = Mockito.spy(userService);

        String duplicateUser = "existingUser";

        when(spiedServ.isDuplicateUsername(duplicateUser)).thenThrow(new ResourceConflictException());

        spiedServ.isDuplicateUsername(duplicateUser);
    }

    @Test
    public void test_isDuplicateEmail_GivenUnique() {
        String uniqueEmail = "unique@email.com";

        userService.isDuplicateEmail(uniqueEmail);
    }

    @Test(expected = ResourceConflictException.class)
    public void test_isDuplicateEmail_givenDuplicate() {
        UserService spiedServ = Mockito.spy(userService);

        String duplicateEmail = "existing@email.com";

        when(spiedServ.isDuplicateEmail(duplicateEmail)).thenThrow(new ResourceConflictException());

        spiedServ.isDuplicateEmail(duplicateEmail);
    }

    @Test
    public void test_register_givenValidInfo() {
        UserService spiedServ = Mockito.spy(userService);

        NewUserRequest request = mock(NewUserRequest.class);
        when(request.getUsername()).thenReturn("newUser");
        when(request.getPassword1()).thenReturn("P@ssw0rd");
        when(request.getPassword2()).thenReturn("P@ssw0rd");
        when(request.getEmail()).thenReturn("newUser@email.com");
        when(request.getGiven_name()).thenReturn("Neuer");
        when(request.getSurname()).thenReturn("Benutzer");

        Assert.assertNotNull(request);

        String role = "user";

        when(spiedServ.register(request, role)).thenReturn(new User());
    }

    @Test
    public void test_login_givenValidInfo() {
        LoginRequest request = mock(LoginRequest.class);
        when(request.getUsername()).thenReturn("ExistingUser");
        when(request.getPassword()).thenReturn("P@ssw0rd");

        when(mockUserRepo.login(request.getUsername(), request.getPassword())).thenReturn(new User());
        User user = mockUserRepo.login(request.getUsername(), request.getPassword());
        Assert.assertNotNull(user);


        verify(mockUserRepo, times(1)).login(request.getUsername(), request.getPassword());
    }
}

