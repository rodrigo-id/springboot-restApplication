package com.example.springboot.restservice.restApiExample.repository;

import com.example.springboot.restservice.restApiExample.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
/*
* SPRING REST DATA nos pormite exponer los metodos de la interfaz CRUD, sin necesidad de tener que crear SERVICES
* o CONTROLLERS
*
* Introduction to Spring Data Rest
Hit http://localhost:8080/users-rest-data in POSTMAN
http://localhost:8080/users-rest-data?page=1&size2
http://localhost:8080/users-rest-data/1
http://localhost:8080/users-rest-data/?size=4
http://localhost:8080/users-rest-data/?sort=name,desc
@Param("rol")
http://localhost:8080/users-rest-data/search/findByRol?rol=mama
* */


//path expone la ruta, collectionsResourcesRel muestra los links generados para las consultas realizadas
@RepositoryRestResource(path="users-rest-data", collectionResourceRel = "users")
public interface ImpUserRepository extends PagingAndSortingRepository<User, Long> {
    /*
    * Creamos un metodo que especificamenete retorna los usuarios buscando por un rol especifico.
    * Esto funciona con la magia de spring donde al usar las palabras claves 'findBy{NombreColumna}'
    * nos crea la implementacion del metodo de busqueda de usuarios por rol, en la clase entity tenemos
    * un atributo que se llama rol, y spring realiza toda la magia para hacer funcionar el metodo
    *
    * Exponemos nuestro metodo creado a traves del @param y usamos la siguiente url como ejemplo
    * http://localhost:8080/users-rest-data/search/findByRol?rol=mama
    * */
    List<User> findByRol(@Param("rol") String rol);

}
