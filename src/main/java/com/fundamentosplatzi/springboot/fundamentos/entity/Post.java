package com.fundamentosplatzi.springboot.fundamentos.entity;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //para crear un id unico
    @Column(name ="id_post", nullable = false, unique = true ) //se puede utilizar para asignarle un nombre en especifico a la columna en la base
    private Long id;
    @Column(name = "description", length = 255) //asignar el nombre y el tamaño a la columna
    private String description;
    @ManyToOne
    private User user; //se crea la relacion muchos a uno por que muchos post tienen un solo usuario

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
