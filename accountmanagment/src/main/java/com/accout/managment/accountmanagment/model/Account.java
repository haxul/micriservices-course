package com.accout.managment.accountmanagment.model;


import lombok.*;
import org.springframework.mail.MailAuthenticationException;

import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class Account {
    private int age;
    private int id;
    private String name;
    private String surname;

    @SneakyThrows
    public void sayHello() {
        throw new MailAuthenticationException("exeption");
    }

    public void show() {
           val list = new ArrayList<>();
           list.add(1);
           list.add(2);

    }

}
