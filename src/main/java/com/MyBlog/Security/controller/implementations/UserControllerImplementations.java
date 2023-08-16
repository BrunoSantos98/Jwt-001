package com.MyBlog.Security.controller.implementations;

import com.MyBlog.Security.configs.security.TokenService;
import com.MyBlog.Security.controller.UserController;
import com.MyBlog.Security.dto.LoginDto;
import com.MyBlog.Security.dto.LoginResponseDtoo;
import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/user")
public class UserControllerImplementations implements UserController {

    private final UserServices services;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserControllerImplementations(UserServices services, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.services = services;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody UserCadasterDto usuarioCadastro) {
        return ResponseEntity.ok(services.registerNewUser(usuarioCadastro));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDtoo(token));
    }


    @Override
    @GetMapping("/findUserByUsername")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity findUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(services.findUserByUsername(username));
    }

    @Override
    @GetMapping("/findUserByEmail")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(services.findUserByEmail(email));
    }
}
