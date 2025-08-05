package com.myproject.operations.commands;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;

import java.util.Scanner;

public class AccountWithdrawCommand implements OperationCommand {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountWithdrawCommand(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID to withdraw from: ");
        Long accountWithdraw = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to withdraw: ");
        int moneyToWithdraw = Integer.parseInt(scanner.nextLine());
        accountService.moneyWithDraw(accountWithdraw,moneyToWithdraw);
        System.out.printf("Amount %d withdraw from account ID %d.", moneyToWithdraw, accountWithdraw);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_WITHDRAW;
    }
}
