package com.myproject.operations.commands;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import com.myproject.service.AccountService;

import java.util.Scanner;

public class AccountTransferCommand implements OperationCommand {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferCommand(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter source account ID: ");
        Long accountIdFrom = Long.parseLong(scanner.nextLine());
        System.out.println("Enter target account ID: ");
        Long accountIdTo = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to transfer: ");
        int moneyToTrasfer = Integer.parseInt(scanner.nextLine());

        accountService.moneyTransfer(accountIdFrom, accountIdTo, moneyToTrasfer);
        System.out.printf("Amount %d transferred from account ID %d to account ID  %d.", moneyToTrasfer, accountIdFrom, accountIdTo);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ACCOUNT_TRANSFER;
    }
}
