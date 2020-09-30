package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.entity.User;
import com.example.springboot.restservice.restApiExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.retrieveAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> retrieveUserById(@PathVariable Long id){
        return userService.retrieveUserById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ResponseEntity<?>> addUsers(@RequestBody List<User> users){
        List<User> createdUsers = userService.addUsers(users);
        if (createdUsers == null || createdUsers.size() <=0){
            return Collections.singletonList(ResponseEntity.noContent().build());
        }

        List<ResponseEntity<?>> responseEntities = new ArrayList<>();
        createdUsers.forEach(user -> {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId()).toUri();
            responseEntities.add(ResponseEntity.created(location).build());
        });
        return responseEntities;
    }
}
