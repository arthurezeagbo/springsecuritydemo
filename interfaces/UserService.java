package com.arthur.springsecuritydemo.interfaces;

import com.arthur.springsecuritydemo.domain.User;

import java.util.List;

public interface UserService{

    List<User> getAllUsers();
    String saveUser(User user);
    String saveAllUsers(List<User> users);

}
