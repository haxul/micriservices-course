package com.usersapp.usersapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final Environment environment;

    public UserController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/status/check")
    public String status() {

        return "Current port " + environment.getProperty("local.server.port");
    }
}
