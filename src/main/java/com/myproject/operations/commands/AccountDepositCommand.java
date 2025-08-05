package com.myproject.operations.commands;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;

import java.util.Scanner;

public class AccountDepositCommand implements OperationCommand {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountDepositCommand(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID: ");
        long accountToDeposit = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to deposit: ");
        int moneyToDeposit = Integer.parseInt(scanner.nextLine());
        accountService.moneyDeposit(accountToDeposit,moneyToDeposit);
        System.out.printf("Amount %d deposit to account ID %d.", moneyToDeposit, accountToDeposit);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_DEPOSIT;
    }
}
