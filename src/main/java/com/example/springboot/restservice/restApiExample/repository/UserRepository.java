package com.example.springboot.restservice.restApiExample.repository;

import com.example.springboot.restservice.restApiExample.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    /*
    * Creamos un metodo que especificamenete retorna los usuarios buscando por un rol especifico.
    * Esto funciona con la magia de spring donde al usar las palabras claves 'findBy{NombreColumna}'
    * nos crea la implementacion del metodo de busqueda de usuarios por rol, en la clase entity tenemos
    * un atributo que se llama rol, y spring realiza toda la magia para hacer funcionar el metodo
    * */
    List<User> findByRol(String rol);

}
