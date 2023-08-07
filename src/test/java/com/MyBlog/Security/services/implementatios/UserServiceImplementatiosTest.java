package com.MyBlog.Security.services.implementatios;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.exceptions.ObjectConflictException;
import com.MyBlog.Security.exceptions.ObjectNotFoundException;
import com.MyBlog.Security.models.User;
import com.MyBlog.Security.repository.UserRepository;
import com.MyBlog.Security.enums.Role;
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
class UserServiceImplementatiosTest {


    @InjectMocks
    private UserServiceImplementatios implementation;
    @Mock
    private UserRepository repository;

    protected User usuario = new User(UUID.randomUUID(), "Carlos Amorim", "Carlos001", "senha1234", Role.USER, "email@email.com");
    protected UserCadasterDto usuarioCadastro = new UserCadasterDto("Carlos Amorim", "Carlos001", "senha1234", "email@email.com");

    @Test
    @DisplayName("Deve cadastrar o usuario novo")
    public void shouldBeRegisterNewUser(){
        given(repository.existsByUsername(usuarioCadastro.username())).willReturn(false);
        given(repository.existsByEmail(usuarioCadastro.email())).willReturn(false);
        given(repository.save(any(User.class))).willReturn(usuario);

        implementation.registerNewUser(usuarioCadastro);

        verify(repository,times(1)).save(any(User.class));
        verify(repository,times(1)).existsByUsername(usuarioCadastro.username());
        verify(repository,times(1)).existsByEmail(usuarioCadastro.email());
    }

    @Test
    @DisplayName("não cadastra usuario novo por existir email")
    public void shouldBeNotRegisterNewUserBySameEmail(){
        given(repository.existsByEmail(usuarioCadastro.email())).willReturn(true);

        ObjectConflictException e = assertThrows(ObjectConflictException.class,
                () -> implementation.registerNewUser(usuarioCadastro));

        verify(repository,times(1)).existsByEmail(usuarioCadastro.email());
        assertEquals("Email ja cadastrado na base de dados",e.getMessage());
    }

    @Test
    @DisplayName("não cadastra usuario novo por existir username")
    public void shouldBeNotRegisterNewUserBySameUsername(){
        given(repository.existsByEmail(usuarioCadastro.email())).willReturn(false);
        given(repository.existsByUsername(usuarioCadastro.username())).willReturn(true);

        ObjectConflictException e = assertThrows(ObjectConflictException.class,
                () -> implementation.registerNewUser(usuarioCadastro));

        verify(repository,times(1)).existsByEmail(usuarioCadastro.email());
        verify(repository,times(1)).existsByUsername(usuarioCadastro.username());
        assertEquals("Nome de usuario ja cadastrado na base de dados",e.getMessage());
    }

    @Test
    @DisplayName("Encontrando um usuario pelo username")
    public void shouldBeFindUserByUsername(){
        given(repository.existsByUsername(usuarioCadastro.username())).willReturn(true);
        given(repository.findByUsername(usuarioCadastro.username())).willReturn(usuario);

        implementation.findUserByUsername(usuarioCadastro.username());

        verify(repository,times(1)).existsByUsername(usuarioCadastro.username());
        verify(repository,times(1)).findByUsername(usuarioCadastro.username());
    }

    @Test
    @DisplayName("Falha ao encontrar usuario pelo username")
    public void shouldBeNotFindUserByUsername(){
        given(repository.existsByUsername(usuarioCadastro.username())).willReturn(false);

        ObjectNotFoundException e = assertThrows(ObjectNotFoundException.class,
                () -> implementation.findUserByUsername(usuarioCadastro.username()));

        verify(repository,times(1)).existsByUsername(usuarioCadastro.username());
        assertEquals("Usuario não encontrado na base de dados através do username fornecido", e.getMessage());
    }
}