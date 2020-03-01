package com.accout.managment.accountmanagment.model;

import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;

@NoArgsConstructor
public class Lion {

    @PostConstruct
    public void init() {
        System.out.println("init bean");
    }

    public void test() {
        System.out.println("hello the lion");
    }
}
