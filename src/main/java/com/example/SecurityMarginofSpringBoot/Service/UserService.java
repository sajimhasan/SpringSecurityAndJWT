package com.example.SecurityMarginofSpringBoot.Service;


import com.example.SecurityMarginofSpringBoot.Model.Users;
import com.example.SecurityMarginofSpringBoot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public Users makeusers(Users users){
        return userRepo.save(users);
    }
}
