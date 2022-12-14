package com.revature.pokecare.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String user_id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "given_name", nullable = false)
    private String given_name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "salt", nullable = false)
    private byte[] salt;

    @Column(name = "bio")
    private String bio;

    @Column(name = "last_login")
    private Timestamp last_login;

    @OneToMany (
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Pokemon> pokemon;

    public User() {
    }

    public User(String user_id) {
        this.user_id = user_id;
    }

    public User(String user_id, String username, String password, String given_name, String surname, String email, String role, boolean isActive, byte[] salt, String bio, Timestamp last_login) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.salt = salt;
        this.bio = bio;
        this.last_login = last_login;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive + + '\'' +
                ", bio='" + bio +
                '}';
    }
}
