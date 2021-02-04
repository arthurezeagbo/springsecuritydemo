package com.arthur.springsecuritydemo.impl;

import com.arthur.springsecuritydemo.domain.User;
import com.arthur.springsecuritydemo.interfaces.UserService;
import com.arthur.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String saveUser(User user) {
        try
        {
            userRepository.save(user);

        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
        return "User saved!";
    }

    @Override
    public String saveAllUsers(List<User> users) {
        try
        {
            userRepository.saveAll(users);

        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
        return "Users saved!";
    }
}

