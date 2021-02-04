package com.arthur.springsecuritydemo.domain;

import com.arthur.springsecuritydemo.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        List<User> users = new ArrayList<>();

        User admin = new User("admin@gmail.com"
                ,passwordEncoder.encode("password")
                ,"admin@gmail.com",true,true, "ADMIN_ROLE","admin");

        User user = new User("user@gmail.com"
                ,passwordEncoder.encode("password")
                ,"user@gmail.com",true,true, "USER_ROLE","user");

        List<User> usersList = Arrays.asList(admin,user);
        try
        {
            userService.saveAllUsers(usersList);
        }
        catch(Exception exception)
        {
            System.out.println("Error adding users to database ------->>"+exception.getMessage());
        }

        System.out.println("Database initialized");

    }
}

