package com.MyBlog.Security.controller.implementations;

import com.MyBlog.Security.controller.UserController;
import com.MyBlog.Security.services.UserServices;

public class UserControllerImplementations implements UserController {

    private final UserServices services;

    public UserControllerImplementations(UserServices services) {
        this.services = services;
    }
}
