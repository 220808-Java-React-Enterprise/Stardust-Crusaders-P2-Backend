package com.revature.pokecare.repositories;

import com.revature.pokecare.dtos.requests.NewUserRequest;
import com.revature.pokecare.dtos.responses.Principal;
import com.revature.pokecare.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User login(String username, String password);

    @Query(value = "SELECT username FROM users WHERE username = ?1", nativeQuery = true)
    String findUsername(String username);

    @Query(value = "SELECT password FROM users WHERE user_id = ?1", nativeQuery = true)
    String getPassword(String id);

    @Query(value = "SELECT email FROM users WHERE email = ?1", nativeQuery = true)
    String findEmail(String email);

    @Query(value = "SELECT salt FROM users WHERE username = ?1", nativeQuery = true)
    byte[] getSalt(String username);

    @Query(value = "SELECT salt FROM users WHERE user_id = ?1", nativeQuery = true)
    byte[] getSaltById(String id);

    @Query(value = "SELECT bio FROM users WHERE user_id = ?1", nativeQuery = true)
    String getBioById(String id);

    @Query(value = "SELECT username FROM users WHERE username = ?1", nativeQuery = true)
    User getByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET is_active = ?2 WHERE user_id = ?1", nativeQuery = true)
    void setActive(String id, boolean isActive);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET username = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeUsername(String id, String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET (password, salt) = (?2, ?3) WHERE user_id = ?1", nativeQuery = true)
    void changePassword(String id, String password, byte[] salt);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET email = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeEmail(String id, String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET given_name = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeGivenName(String id, String givenName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET surname = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeSurname(String id, String surname);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET bio = ?2 WHERE user_id = ?1", nativeQuery = true)
    void updateBio(String id, String bio);

    @Query(value = "SELECT last_login FROM users WHERE user_id = ?1", nativeQuery = true)
    Timestamp getLastLogin(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET last_login = ?1 WHERE user_id = ?2", nativeQuery = true)
    void updateLastLogin(Timestamp ts, String id);
}
