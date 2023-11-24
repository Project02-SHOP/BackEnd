package com.b2.prj02.exception.cart;

public class UserCartNotFoundException extends RuntimeException {
    public UserCartNotFoundException(String message) {
        super(message);
    }

}