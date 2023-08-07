package com.MyBlog.Security.services;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    UserDTO registerNewUser(UserCadasterDto usuarioCadastro);
    UserDTO findUserByUsername(String username);
}
