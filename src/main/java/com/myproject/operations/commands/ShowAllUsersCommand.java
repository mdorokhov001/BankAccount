package com.myproject.operations.commands;

import com.myproject.model.User;
import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.UserService;

import java.util.List;
import java.util.Scanner;

public class ShowAllUsersCommand implements OperationCommand {

    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        List<User> users = userService.getAllUsers();
        System.out.println("List of all users:");
        users.forEach(System.out::println);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.SHOW_ALL_USERS;
    }
}
