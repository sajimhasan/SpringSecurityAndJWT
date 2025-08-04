package com.example.SecurityMarginofSpringBoot.Config;

import com.example.SecurityMarginofSpringBoot.Service.JWTService;
import com.example.SecurityMarginofSpringBoot.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.catalina.core.ApplicationContext;

import java.io.IOException;

public class Jwtfilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    org.springframework.context.ApplicationContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YWliYSIsImlhdCI6MTc1NDMwNjU0NiwiZXhwIjoxNzU0MzA2NjU0fQ.lIa83nA3f6H6U5S8V1apuSC1M4m7xi_FsCciOEi_eNk

        String authHeder= request.getHeader("Authorization");
        String token = null ;
        String username= null ;

        if (authHeder != null && authHeder.startsWith("Bearer ")){
            token = authHeder.substring(7);
            username = jwtService.extratUserName(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null ){

            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            if (jwtService.vaildatetoken(token , userDetails)){
                UsernamePasswordAuthenticationToken authtoken=

                        new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

            }
        }


    }
}
