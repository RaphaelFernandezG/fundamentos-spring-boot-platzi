package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;

import java.util.List;

public interface GetUser {  //se cambia el class por el interface para retribuir la lista de usuarios
    List<User> getAll();
}
