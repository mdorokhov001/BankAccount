package com.myproject.operations.commands;

import com.myproject.model.User;
import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.UserService;

import java.util.Scanner;

public class CreateUserCommand implements OperationCommand {

    private final Scanner scanner;
    private final UserService userService;

    public CreateUserCommand(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Enter login for new user: ");
        String loginInput = scanner.nextLine();
        User user = userService.createUser(loginInput);
        System.out.println("User created: " + user);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.USER_CREATE;
    }
}
