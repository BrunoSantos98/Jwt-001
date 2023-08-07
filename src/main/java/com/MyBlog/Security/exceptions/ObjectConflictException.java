package com.MyBlog.Security.exceptions;

public class ObjectConflictException extends RuntimeException{
    public ObjectConflictException(String message) {
        super(message);
    }
}
