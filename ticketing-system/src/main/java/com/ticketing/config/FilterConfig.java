package com.ticketing.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ticketing.security.JwtAuthFilter;


@Configuration
public class FilterConfig {


    private final JwtAuthFilter jwtAuthFilter;


    public FilterConfig(
            JwtAuthFilter jwtAuthFilter
    ) {

        this.jwtAuthFilter = jwtAuthFilter;
    }



    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilterRegistration() {


        FilterRegistrationBean<JwtAuthFilter> registration =
                new FilterRegistrationBean<>();


        registration.setFilter(
                jwtAuthFilter
        );


        registration.addUrlPatterns(
                "/categories/*",
                "/expenses/*",
                "/transport-bookings/*",
                "/accommodation-bookings/*"
        );


        registration.setOrder(1);


        return registration;
    }
}