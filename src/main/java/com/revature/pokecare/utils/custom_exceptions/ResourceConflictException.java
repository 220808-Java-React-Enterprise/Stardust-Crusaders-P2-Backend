package com.revature.pokecare.utils.custom_exceptions;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException() {
    }

    public ResourceConflictException(String message) {
        super(message);
    }
}
