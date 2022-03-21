package com.trainingfresher.sampleservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Set forbidden for path
        // http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/users/3").permitAll();

        // Allow access path
        http.authorizeRequests().antMatchers("v3/api-docs", "/swagger-ui.html", "/actuator/**").permitAll();

       // http.csrf().disable().authorizeRequests().anyRequest().permitAll();

    }

}