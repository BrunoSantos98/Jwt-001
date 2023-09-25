package com.MyBlog.Security.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
