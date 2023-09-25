package com.MyBlog.Security.dto;

import com.MyBlog.Security.enums.Role;

public record UserDTO(String name, String username, Role role, String email) {
}
