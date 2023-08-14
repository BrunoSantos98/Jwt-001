package com.MyBlog.Security.controller;

import com.MyBlog.Security.dto.UserCadasterDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    public ResponseEntity registerNewUser(UserCadasterDto usuarioCadastro);
    public ResponseEntity findUserByUsername(String username);
}
