package com.revature.pokecare.dtos.responses;

//import javax.persistence.Entity;
//import javax.persistence.Id;

public class Principal {

    private String id;

    private String username;

    private String role;

    private boolean isActive;

    public Principal() {
    }

    public Principal(String id, String username, String role, boolean isActive) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
