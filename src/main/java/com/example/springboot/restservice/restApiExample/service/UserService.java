package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.entity.User;
import com.example.springboot.restservice.restApiExample.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private IUserRepository IUserRepository;

    @Autowired
    public void setUserRepository(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public List<User> retrieveAllUsers(){
        return (List<User>) IUserRepository.findAll();
    }

    public Optional<User> retrieveUserById(Long id){
        return IUserRepository.findById(id);
    }

    public List<User> addUsers(List<User> users){
        return (List<User>) IUserRepository.saveAll(
                users.stream()
                        .filter(user -> user.getName() != null)
                        .collect(Collectors.toList())
        );
    }

    public List<User> retrieveUsersByRol(String rol){
        return IUserRepository.findByRol(rol);
    }
}
