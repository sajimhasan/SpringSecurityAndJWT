package com.example.SecurityMarginofSpringBoot.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.config.Customizer;

@EnableWebSecurity
@Component
public class SpringSecurity {

    @Autowired
    private Jwtfilter jwtfilter;

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


            return http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(request -> request
                            .requestMatchers("/registration", "/login").permitAll()
                            .anyRequest().authenticated())
//                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtfilter , UsernamePasswordAuthenticationFilter.class)
                    .build();


//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrt= new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//
//                httpSecurityCsrfConfigurer.disable();
//
//            }
//        };

//        http.csrf(custCsrt);



    }
   // make the default user or admin !! but is not for production !!
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1= User.withDefaultPasswordEncoder()
//                .username("israt")
//                .password("fuckyou")
//                .roles("user")
//                .build();
//        UserDetails user2= User.withDefaultPasswordEncoder()
//                .username("taibo")
//                .password("goodgirl")
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        // when i use this then don't use the normal password !!
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        // this for use normal password !!-->
        //provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }
}

//fix the bugs the problem is when enter the vaild password the postman say unauthorized 401 !!! the problem in Springsecurity --> line 75 and 77 
