package com.ticketing.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SecurityConfig {



    private final JwtAuthFilter jwtAuthFilter;






    public SecurityConfig(
            JwtAuthFilter jwtAuthFilter
    ) {

        this.jwtAuthFilter = jwtAuthFilter;

    }









    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {



        http

        .cors(
                cors -> {}
        )

        .csrf(
                csrf -> csrf.disable()
        )



        .sessionManagement(

                session ->

                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )

        )







        .authorizeHttpRequests(

                auth -> auth






                // PUBLIC

                .requestMatchers(
                        "/auth/**"
                )
                .permitAll()







                // ADMIN DASHBOARD

                .requestMatchers(
                        "/admin/**"
                )
                .hasRole(
                        "ADMIN"
                )









                // ADMIN CREATE TRANSPORT

                .requestMatchers(

                        HttpMethod.POST,

                        "/transportation",
                        "/accommodation"

                )

                .hasRole(
                        "ADMIN"
                )








                // USER + ADMIN VIEW DATA

                .requestMatchers(

                        HttpMethod.GET,

                        "/transportation/**",
                        "/accommodation/**"

                )

                .hasAnyRole(
                        "USER",
                        "ADMIN"
                )








                // BOOKINGS

                .requestMatchers(

                        "/transport-bookings/**",
                        "/accommodation-bookings/**",
                        "/payments/**",
                        "/users/**"

                )

                .hasAnyRole(
                        "USER",
                        "ADMIN"
                )








                .anyRequest()
                .authenticated()


        )






        .addFilterBefore(

                jwtAuthFilter,

                UsernamePasswordAuthenticationFilter.class

        );






        return http.build();

    }


}