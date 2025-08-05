package com.myproject.operations.commands;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;
import com.myproject.service.UserService;

import java.util.Scanner;

public class AccountCloseCommand implements OperationCommand {

    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public AccountCloseCommand(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID to close:");
        Long accId = Long.parseLong(scanner.nextLine());
        accountService.closeAccount(accId, userService);
        System.out.printf("Account with ID %d has been closed.", accId);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
