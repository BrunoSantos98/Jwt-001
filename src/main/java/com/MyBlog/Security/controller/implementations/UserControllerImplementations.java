package com.MyBlog.Security.controller.implementations;

import com.MyBlog.Security.controller.UserController;
import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/user")
public class UserControllerImplementations implements UserController {

    private final UserServices services;

    public UserControllerImplementations(UserServices services) {
        this.services = services;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody UserCadasterDto usuarioCadastro) {
        return ResponseEntity.ok(services.registerNewUser(usuarioCadastro));
    }

    @Override
    @GetMapping("/findUserByUsername")
    public ResponseEntity findUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(services.findUserByUsername(username));
    }
}
