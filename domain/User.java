package com.arthur.springsecuritydemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;

    private Boolean isEnabled;
    private Boolean isNonLocked;
    private String userRoles = "";
    private String userPermissions = "";

    public User(String username
            , String password
            , String email
            , Boolean isEnabled
            , Boolean isNonLocked
            , String userRoles
            ,String userPermissions) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
        this.isNonLocked = isNonLocked;
        this.userRoles = userRoles;
        this.userPermissions = userPermissions;
    }

    private List<String> getRoleList(){
        if(getUserRoles().length() > 0)
        {
            String[] roles = getUserRoles().split(",");
            return Arrays.asList(roles);

        }
        return new ArrayList<String>();
    }

    private List<String> getPermissionList(){
        if(getUserPermissions().length() > 0)
        {
            String[] roles = getUserPermissions().split(",");
            return Arrays.asList(roles);
        }
        return new ArrayList<String>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        getRoleList().forEach(c ->{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(c);
            authorities.add(authority);
        });

        getPermissionList().forEach(p -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        return authorities;
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
        return this.getIsNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getIsEnabled();
    }
}

