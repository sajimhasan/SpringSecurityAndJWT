package com.example.SecurityMarginofSpringBoot.Service;


import com.example.SecurityMarginofSpringBoot.Model.Users;
import com.example.SecurityMarginofSpringBoot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;


    public Users makeusers(Users users){
        return userRepo.save(users);
    }
    public String varify(Users users){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        if (authentication.isAuthenticated()){
            return jwtService.genearetedToken(users.getUsername());
        }else {
            return "fail";
        }
    }
}
