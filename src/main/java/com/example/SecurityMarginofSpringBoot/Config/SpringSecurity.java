package com.example.SecurityMarginofSpringBoot.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.security.config.Customizer;

@EnableWebSecurity
@Component
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

//        http.csrf(customizer-> customizer.disable());
//        http.authorizeHttpRequests(request-> request.anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        Customizer<CsrfConfigurer<HttpSecurity>> custCsrt= new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {

                httpSecurityCsrfConfigurer.disable();

            }
        };

        http.csrf(custCsrt);

        return http.build();

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
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
