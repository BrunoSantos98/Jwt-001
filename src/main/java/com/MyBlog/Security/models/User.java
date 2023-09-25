package com.MyBlog.Security.models;

import com.MyBlog.Security.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Data
public class User implements Serializable, UserDetails {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 5)
    private Role role;
    @Column(nullable = false, unique = true, length = 125)
    private String email;

    public User() {
    }

    public User(UUID id, String name, String username, String password, Role role, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
