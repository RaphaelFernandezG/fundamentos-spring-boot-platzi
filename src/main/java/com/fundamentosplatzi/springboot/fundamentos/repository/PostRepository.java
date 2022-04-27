package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //se usa para poderlo inyectar de dependencia
public interface PostRepository extends JpaRepository<Post, Long> {  //entidad a mapear y el tipo de dato

}
