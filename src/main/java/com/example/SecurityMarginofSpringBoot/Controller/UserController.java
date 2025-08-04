package com.example.SecurityMarginofSpringBoot.Controller;


import com.example.SecurityMarginofSpringBoot.Model.Users;
import com.example.SecurityMarginofSpringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);


    @PostMapping("/registration")
    public Users createuser(@RequestBody Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userService.makeusers(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        System.out.println(users);
        return userService.varify(users);
    }
}
