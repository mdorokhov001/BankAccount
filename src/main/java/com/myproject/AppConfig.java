package com.myproject;

import com.myproject.service.AccountService;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationsConsoleListener operationsConsoleListener(Scanner scanner, UserService userService, AccountService accountService){
        return new OperationsConsoleListener(scanner, userService, accountService);
    }

    @Bean
    public UserService userService(AccountService accountService){
        return new UserService(accountService);
    }

    @Bean
    public AccountService accountService(
            @Value("${account.default-amount}") int defaultAmount,
            @Value("${account.transfer-commission}") double transferCommision
    ){
        return new AccountService(defaultAmount, transferCommision);
    }
}
