package com.revature.pokecare.dtos.requests;

public class UserIdRequest {
    private String user_id;

    public UserIdRequest() {
    }

    public UserIdRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserIdRequest{" +
                "user_id='" + user_id + '\'' +
                '}';
    }
}
