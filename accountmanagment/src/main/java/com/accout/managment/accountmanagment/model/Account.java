package com.accout.managment.accountmanagment.model;


import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
public class Account {
    private int age;
    private int id;
    private String name;
    private String surname;

}
