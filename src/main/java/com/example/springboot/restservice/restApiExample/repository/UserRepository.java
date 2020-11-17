package com.example.springboot.restservice.restApiExample.repository;

import com.example.springboot.restservice.restApiExample.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /*
    * Creamos un metodo que especificamenete retorna los usuarios buscando por un rol especifico.
    * Esto funciona con la magia de spring donde al usar las palabras claves 'findBy{NombreColumna}'
    * nos crea la implementacion del metodo de busqueda de usuarios por rol, en la clase entity tenemos
    * un atributo que se llama rol, y spring realiza toda la magia para hacer funcionar el metodo
    * */
    List<User> findByRol(String rol);

    /*
        Este metodo lo saque de internet y muestra como crear querys personalizadas en caso de ser necesarias

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password,
                        @Param("id") Long id);
                        
     */

}
