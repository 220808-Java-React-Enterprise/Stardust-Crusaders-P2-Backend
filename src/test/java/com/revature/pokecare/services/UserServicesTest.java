package com.revature.pokecare.services;

import com.revature.pokecare.repositories.UserRepository;
import com.revature.pokecare.utils.HashConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


public class UserServicesTest {
    private UserService user;

    private final UserRepository mockUserRepo = mock(UserRepository.class);
    private final HashConfig hash = mock(HashConfig.class);

    @Before
    public void setup() {
        user = new UserService(mockUserRepo, hash);
    }

    @Test
    public void test_IsValidPassword_GivenGoodPassword() {
        //Arrange
        String validPass = "Revature90@";

        //Act
        boolean flag = user.isValidPassword(validPass);

        //Assert
        Assert.assertTrue(flag);

    }

    @Test
    public void test_isValidUsername_GivenGood() {
        String valid = "Matthew88";
        boolean flag = user.isValidUsername(valid);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isSamePassword_GivenGood() {
        String pass = "Revature123@";
        String pass2 = "Revature123@";
        boolean flag = user.isSamePassword(pass, pass2);
        Assert.assertTrue(flag);
    }
}
