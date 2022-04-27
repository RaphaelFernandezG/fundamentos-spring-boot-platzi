package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.services.UserService;
import org.springframework.stereotype.Component;

@Component  //se usa cuando se quiere hacer algo bien general como en este caso que son casos de uso
public class DeleteUser {

    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.delete(id);
    }
}
