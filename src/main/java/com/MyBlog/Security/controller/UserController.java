package com.MyBlog.Security.controller;

import com.MyBlog.Security.dto.UserCadasterDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity registerNewUser(UserCadasterDto usuarioCadastro);
    ResponseEntity findUserByUsername(String username);
    ResponseEntity findByEmail(String email);
}
