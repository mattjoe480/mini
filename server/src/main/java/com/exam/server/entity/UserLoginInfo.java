package com.exam.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo implements UserDetails {
    private static final Logger log = LogManager.getLogger(UserLoginInfo.class);
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private Integer phoneNo;

    private String password;

    public UserLoginInfo(String name, String email,
                         Integer integer, String password) {
        this.name = name;
        this.email = email;
        this.phoneNo = integer;
        this.password = password;
        this.role = Role.USER;
    }
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
