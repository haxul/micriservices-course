package com.accout.managment.accountmanagment.controllers;

import com.accout.managment.accountmanagment.model.Account;
import com.accout.managment.accountmanagment.model.Lion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/account")
public class AccountManagement {

    public final Lion lion;

    public AccountManagement(Lion lion) {
        this.lion = lion;
    }

    @PostConstruct
    public void init() {
        System.out.println("another init");
    }

    @GetMapping
    public String status(){
        return "rest contr";
    }

    @PostMapping
    public Account test(@RequestBody Account account) {

        Account account1 = Account.builder().age(1).id(1).name("Sergey").surname("Starodubov").build();
        account1.show();
        return account1;
    }
}
