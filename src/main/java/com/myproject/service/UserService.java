package com.myproject.service;

import com.myproject.model.Account;
import org.springframework.stereotype.Service;
import com.myproject.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private Map<Long, User> users;
    private Set<String> logins;
    private AccountService accountService;
    private final AtomicLong nextId = new AtomicLong(1);

    public UserService(AccountService accountService){
        this.accountService = accountService;
        this.users =  new HashMap<>();
        this.logins = new HashSet<>();
    }

    public List<User> getAllUsers(){
        return users.values().stream().toList();
    }

    public User createUser(String login){
        if (logins.contains(login)){
            throw new IllegalArgumentException("User with login \"%s\" already exists \n". formatted(login));
        }

        logins.add(login);
        long id = nextId.getAndIncrement();
        User user = new User(id, login, new ArrayList<>());
        Account newAccount = accountService.createAccount(user);
        user.getAccountList().add(newAccount);
        users.put(id, user);
        return user;
    }

    public Optional<User> getUserById(Long userId){
        return Optional.ofNullable(users.get(userId));
    }


}
