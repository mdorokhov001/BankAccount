package com.myproject.operations.commands;

import com.myproject.model.User;
import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;
import com.myproject.service.UserService;

import java.util.Scanner;

public class AccountCreateCommand implements OperationCommand {
    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public AccountCreateCommand(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter the user id for which to create an account: ");
        Long userId = Long.parseLong(scanner.nextLine());
        User currentUser = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No such user with id = %d".formatted(userId)));
        var account = accountService.createAccount(currentUser);

        currentUser.getAccountList().add(account);

        System.out.printf("New account created with ID: %d for user: %s", account.getId(), currentUser.getLogin());
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_CREATE;
    }
}
