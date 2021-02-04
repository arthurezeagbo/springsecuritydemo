package com.arthur.springsecuritydemo.controller;

import com.arthur.springsecuritydemo.domain.User;
import com.arthur.springsecuritydemo.interfaces.UserService;
import com.arthur.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("welcome")
    public String sayWelcome(){
        return "Welcome";
    }

    @RequestMapping(path = "show-users",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
