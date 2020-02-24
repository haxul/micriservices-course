package com.usersapp.usersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersappApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersappApplication.class, args);
    }

}
