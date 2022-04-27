package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository   //se usa para poderlo inyectar de dependencia
//public interface UserRepository extends JpaRepository<User, Long> { //entidad a mapear y el tipo de dato

public interface UserRepository extends PagingAndSortingRepository<User, Long> {  //se cambiara jparepository por
                        // pagingandsortingrepository la cual contiene
                        //todos los metodos relaciolados con el paging para poder utilizar la paginacion


    @Query("Select u from User u WHERE u.email=?1")  //igual a un parametro =?1
    Optional<User> finByUserEmail(String email);
    @Query("Select u from User u WHERE u.name like ?1%")  //buscar y ordenar a partir del nombre
    List<User> findAndSort(String name, Sort sort);

        //QueryMethods

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);  //se usa para buscar no una lista sino un usuario simple

    List<User> findByNameLike(String name);

    List<User> findByPosts_Description(String description);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);    //si se quiere hacer la misma busqueda pero sin el like se puede usar asi

    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate) " +
            "FROM User u " +
            "where u.birthDate=:parametroDate " +
            "and u.email=:parametroEmail")   // query a nivel de jsql a nivel de parameters
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroDate") LocalDate date,
                                                @Param("parametroEmail") String email);


    List<User> findAll();
}
