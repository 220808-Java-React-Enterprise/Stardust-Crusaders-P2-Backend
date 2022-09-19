package com.revature.pokecare.dtos.requests;

public class UserInfoRequest {

    private String request;


    public UserInfoRequest() {
    }

    public UserInfoRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "UserInfoRequest{" +
                "request='" + request + '\'' +
                '}';
    }
}
