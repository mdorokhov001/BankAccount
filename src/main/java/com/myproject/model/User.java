package com.myproject.model;

import java.util.List;

public class User {

    final Long id;
    final String login;
    final List<Account> accountList;

    public User(Long id, String login, List<Account> accountList){
        this.id = id;
        this.login = login;
        this.accountList = accountList;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
