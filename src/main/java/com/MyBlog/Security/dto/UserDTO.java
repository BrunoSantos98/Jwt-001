package com.MyBlog.Security.dto;

import enums.Role;

public record UserDTO(String name, String username, String password, Role role, String email) {
}
