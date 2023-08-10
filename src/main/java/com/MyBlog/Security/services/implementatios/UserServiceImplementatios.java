package com.MyBlog.Security.services.implementatios;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.dto.UserDTO;
import com.MyBlog.Security.exceptions.ObjectConflictException;
import com.MyBlog.Security.exceptions.ObjectNotFoundException;
import com.MyBlog.Security.models.User;
import com.MyBlog.Security.repository.UserRepository;
import com.MyBlog.Security.services.UserServices;
import com.MyBlog.Security.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementatios implements UserServices {

    private final UserRepository repository;

    public UserServiceImplementatios(UserRepository repository) {
        this.repository = repository;
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
        User usuario = new User(null, usuarioCadastro.name(), usuarioCadastro.username(), usuarioCadastro.password(),
                Role.USER, usuarioCadastro.email());
        return userToUserDto(repository.save(usuario));
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        if(repository.existsByUsername(username)) {
            return userToUserDto(repository.findByUsername(username));
        }
        throw new ObjectNotFoundException("Usuario não encontrado na base de dados através do username fornecido");
    }
}
