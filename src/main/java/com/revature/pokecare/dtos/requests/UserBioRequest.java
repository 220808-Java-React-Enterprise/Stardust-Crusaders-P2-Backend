package com.revature.pokecare.dtos.requests;

public class UserBioRequest {
    private String bio;

    public UserBioRequest() {
    }

    public UserBioRequest(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "UserBioRequest{" +
                "bio='" + bio + '\'' +
                '}';
    }
}
