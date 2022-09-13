package com.revature.pokecare.dtos.requests;

public class NewUserRequest {

    private String username;
    private String password1;
    private String password2;
    private String given_name;
    private String surname;
    private String email;

    public NewUserRequest() {
    }

    public NewUserRequest(String username, String password1, String password2, String given_name, String surname, String email) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.given_name = given_name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
