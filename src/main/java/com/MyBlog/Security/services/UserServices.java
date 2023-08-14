package com.MyBlog.Security.services;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.dto.UserDTO;

public interface UserServices {
    UserDTO registerNewUser(UserCadasterDto usuarioCadastro);
    UserDTO findUserByUsername(String username);
    UserDTO findUserByEmail(String email);
}
