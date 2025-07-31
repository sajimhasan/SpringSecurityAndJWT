package com.example.SecurityMarginofSpringBoot.Config;


import org.springframework.asm.Type;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.security.config.Customizer

@EnableWebSecurity
@Component
public class SpringSecurity {


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
}
