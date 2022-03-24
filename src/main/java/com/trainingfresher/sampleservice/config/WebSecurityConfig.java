package com.trainingfresher.sampleservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Set forbidden for path
        // http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/users/3").permitAll();

        // Allow access path
        //http.authorizeRequests().antMatchers("v3/api-docs", "/swagger-ui.html", "/actuator/**","/projects").permitAll();

        http.csrf().disable().authorizeRequests().anyRequest().permitAll();

    }

}