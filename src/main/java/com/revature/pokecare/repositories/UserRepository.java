package com.revature.pokecare.repositories;

import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User login(String username, String password);

    @Query(value = "SELECT username FROM users WHERE username = ?1", nativeQuery = true)
    String findUsername(String username);

    @Query(value = "SELECT email FROM users WHERE email = ?1", nativeQuery = true)
    String findEmail(String email);

}
