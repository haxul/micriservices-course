package com.accout.managment.accountmanagment.controllers;

import com.accout.managment.accountmanagment.model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/account")
public class AccountManagement {

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
