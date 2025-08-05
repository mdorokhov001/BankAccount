package com.myproject;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationsConsoleListener operationsConsoleListener(
            Scanner scanner,
            List<OperationCommand> operationCommandList

    ){
        Map<OperationType, OperationCommand> operationCommandMap = operationCommandList
                .stream()
                .collect(
                        Collectors.toMap(
                                operationCommand -> operationCommand.getOperationType(),
                                operationCommand -> operationCommand,
                                (oldVal, newVal) -> oldVal,
                                TreeMap::new
                        )
                );

        return new OperationsConsoleListener(scanner, operationCommandMap);
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
