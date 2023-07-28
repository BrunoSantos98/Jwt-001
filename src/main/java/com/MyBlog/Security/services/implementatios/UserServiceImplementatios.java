package com.MyBlog.Security.services.implementatios;

import com.MyBlog.Security.repository.UserRepository;
import com.MyBlog.Security.services.UserServices;

public class UserServiceImplementatios implements UserServices {

    private final UserRepository repository;

    public UserServiceImplementatios(UserRepository repository) {
        this.repository = repository;
    }
}
