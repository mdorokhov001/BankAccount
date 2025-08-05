package com.myproject.operations;

import com.myproject.operations.commands.*;
import com.myproject.service.AccountService;
import com.myproject.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OparationCommandsConfig {

    @Bean
    public CreateUserCommand createUserCommand(Scanner scanner, UserService userService){
        return new CreateUserCommand(scanner, userService);
    }

    @Bean
    public ShowAllUsersCommand showAllUsersCommand(UserService userService){
        return new ShowAllUsersCommand(userService);
    }

    @Bean
    public AccountCreateCommand accountCreateCommand(Scanner scanner, UserService userService, AccountService accountService){
        return new AccountCreateCommand(scanner, userService, accountService);
    }

    @Bean
    public AccountCloseCommand accountCloseCommand(Scanner scanner, UserService userService, AccountService accountService){
        return new AccountCloseCommand(scanner, userService, accountService);
    }

    @Bean
    public AccountDepositCommand accountDepositCommand(Scanner scanner, AccountService accountService){
        return new AccountDepositCommand(scanner, accountService);
    }

    @Bean
    public AccountWithdrawCommand accountWithdraw(Scanner scanner, AccountService accountService){
        return new AccountWithdrawCommand(scanner, accountService);
    }

    @Bean
    public AccountTransferCommand accountTransferCommand(Scanner scanner, AccountService accountService){
        return new AccountTransferCommand(scanner, accountService);
    }
}
