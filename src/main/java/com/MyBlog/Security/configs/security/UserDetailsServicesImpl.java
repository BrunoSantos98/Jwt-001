package com.MyBlog.Security.configs.security;

import com.MyBlog.Security.exceptions.ObjectNotFoundException;
import com.MyBlog.Security.models.User;
import com.MyBlog.Security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServicesImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServicesImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(repository.findByUsername(username));
        if(user.isEmpty()){
            throw new ObjectNotFoundException("Nome de usuario não encontrado na base de dados");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), user.get().getAuthorities());
    }
}
