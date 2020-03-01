package com.accout.managment.accountmanagment;

import com.accout.managment.accountmanagment.model.Account;
import com.accout.managment.accountmanagment.model.Lion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountmanagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountmanagmentApplication.class, args);
    }

    @Bean
    public Lion bean() {
        return new Lion();
    }
}
