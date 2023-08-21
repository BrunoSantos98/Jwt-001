package com.MyBlog.Security.services.implementatios;

import com.MyBlog.Security.configs.security.TokenService;
import com.MyBlog.Security.dto.LoginDto;
import com.MyBlog.Security.dto.LoginResponseDtoo;
import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.dto.UserDTO;
import com.MyBlog.Security.exceptions.ObjectConflictException;
import com.MyBlog.Security.exceptions.ObjectNotFoundException;
import com.MyBlog.Security.models.User;
import com.MyBlog.Security.repository.UserRepository;
import com.MyBlog.Security.services.UserServices;
import com.MyBlog.Security.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementatios implements UserServices {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserServiceImplementatios(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    private UserDTO userToUserDto(User user) {
        return new UserDTO(user.getName(), user.getUsername(),  user.getRole(), user.getEmail());
    }

    private void verifyIfUserExists(UserCadasterDto usuarioCadastro) {
        if(repository.existsByEmail(usuarioCadastro.email())) {
            throw new ObjectConflictException("Email ja cadastrado na base de dados");
        }else if(repository.existsByUsername(usuarioCadastro.username())) {
            throw new ObjectConflictException("Nome de usuario ja cadastrado na base de dados");
        }
    }

    @Override
    @Transactional
    public UserDTO registerNewUser(UserCadasterDto usuarioCadastro) {
        verifyIfUserExists(usuarioCadastro);
        User usuario = new User(null, usuarioCadastro.name(), usuarioCadastro.username(),
                new BCryptPasswordEncoder().encode(usuarioCadastro.password()), Role.USER, usuarioCadastro.email());
        return userToUserDto(repository.save(usuario));
    }

    @Override
    public LoginResponseDtoo createLoginToken(LoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserDetails) auth.getPrincipal());

        return new LoginResponseDtoo(token);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        if(repository.existsByUsername(username)) {
            return userToUserDto(repository.findByUsername(username));
        }
        throw new ObjectNotFoundException("Usuario não encontrado na base de dados através do username fornecido");
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        if(repository.existsByEmail(email)){
            return userToUserDto(repository.findByEmail(email));
        }
        throw new ObjectNotFoundException("Usuario não encontrado na base de dados através do email fornecido");
    }

}
