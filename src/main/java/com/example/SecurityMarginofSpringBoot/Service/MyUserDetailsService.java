package com.example.SecurityMarginofSpringBoot.Service;


import com.example.SecurityMarginofSpringBoot.Model.UserPinsipal;
import com.example.SecurityMarginofSpringBoot.Repository.UserRepo;
import com.example.SecurityMarginofSpringBoot.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Users user= repo.findByUsername(username);

       if (user == null){
           System.out.println("User is not found");
           throw new UsernameNotFoundException("User is not found");
       }
        return new UserPinsipal(user);
    }
}
