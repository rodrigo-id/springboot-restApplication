package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.entity.User;
import com.example.springboot.restservice.restApiExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> retrieveUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> addUsers(List<User> users){
        return (List<User>)userRepository.saveAll(
                users.stream()
                        .filter(user -> user.getName() != null)
                        .collect(Collectors.toList())
        );
    }
}
