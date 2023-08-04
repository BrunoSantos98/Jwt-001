package com.MyBlog.Security.services;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.models.User;
import com.MyBlog.Security.repository.UserRepository;
import com.MyBlog.Security.services.implementatios.UserServiceImplementatios;
import enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @InjectMocks
    private UserServiceImplementatios implementation;
    @Mock
    private UserRepository repository;

    protected User usuario = new User(UUID.randomUUID(), "Carlos Amorim", "Carlos001", "senha1234", Role.USER, "email@email.com");
    protected UserCadasterDto usuarioCadastro = new UserCadasterDto("Carlos Amorim", "Carlos001", "senha1234", "email@email.com");

    @Test
    @DisplayName("Deve cadastrar o usuario novo")
    private void shouldBeRegisterNewUser(){
        given(repository.existsByUsername(usuarioCadastro.username())).willReturn(false);
        given(repository.existsByEmail(usuarioCadastro.email())).willReturn(false);
        given(repository.save(any(User.class))).willReturn(usuario);

        implementation.registerNewUser(usuarioCadastro);

        verify(repository,times(1)).save(any(User.class));
        verify(repository,times(1)).existsByUsername(usuarioCadastro.username());
        verify(repository,times(1)).existsByEmail(usuarioCadastro.email());
    }
}