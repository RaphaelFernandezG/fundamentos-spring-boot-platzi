package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }
}
