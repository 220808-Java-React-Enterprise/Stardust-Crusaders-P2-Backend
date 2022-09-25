package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.models.User;
import com.revature.pokecare.repositories.UserRepository;
import com.revature.pokecare.utils.HashConfig;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    private final UserRepository mockUserRepo = mock(UserRepository.class);
    private final HashConfig hash = mock(HashConfig.class);

    @BeforeEach
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
}
