package com.example.springboot.restservice.restApiExample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Authentication : User --> Roles
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //Authentication actions: User --> Roles

        /*Enable in memory based authentication with a user named 'user' and 'admin'*/
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}secret1").roles("USER")
                .and()
                .withUser("admin1").password("{noop}secret1").roles("USER", "ADMIN");

    }

    //Authorization: Role --> Access
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic()
                .and().authorizeRequests()
               //this role has access to 'surveys' paths and 'users' paths
                .antMatchers("/surveys/**").hasRole("USER")
                .antMatchers("/users/**").hasRole("USER")
               //the admin role has access to all the paths in this service
                .antMatchers("/**").hasRole("ADMIN")
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }
}
