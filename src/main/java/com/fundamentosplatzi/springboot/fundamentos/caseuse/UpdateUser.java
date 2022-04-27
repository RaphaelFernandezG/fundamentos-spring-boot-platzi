package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component  //se usa cuando se quiere hacer algo bien general como en este caso que son casos de uso
public class UpdateUser {

    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
