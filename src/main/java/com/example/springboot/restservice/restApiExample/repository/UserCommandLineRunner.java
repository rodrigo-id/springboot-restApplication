package com.example.springboot.restservice.restApiExample.repository;

import com.example.springboot.restservice.restApiExample.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Usamos un CommandLineRunner para inyectar data al momento de levantar el proyecto
@Component
public class UserCommandLineRunner implements CommandLineRunner {
    //Asi es como se implementa el log en una clase en springboot
    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Alison", "mama"));
        userRepository.save(new User("Mia", "hijo"));
        userRepository.save(new User("Rodrigo", "papa"));
        userRepository.save(new User("Leon", "hijo"));
        userRepository.save(new User("Leonor", "mama"));

        log.info("-----------START--------------");
        log.info("muestra todos los usuarios por defecto");
        userRepository.findAll().forEach(user -> log.info(user.toString()));
        log.info("muestra todos los usuarios por rol mama");
        userRepository.findByRol("mama").forEach(user -> log.info(user.toString()));

        log.info("------------END-------------");
    }
}
