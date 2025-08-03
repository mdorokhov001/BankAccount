package com.myproject;

import com.myproject.model.OperationTypes;
import com.myproject.model.User;
import com.myproject.service.AccountService;
import com.myproject.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;
@Service
public class OperationsConsoleListener  {

    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public OperationsConsoleListener(Scanner scanner, UserService userService, AccountService accountService){
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    public void start(){
        System.out.println("App is started.");
    }

    public void startListen(){
        while (true) {
            System.out.println("\nPlease enter one of operation type:");
            OperationTypes.getAllOperations().forEach(System.out::println);
            System.out.println();

            String input = scanner.nextLine();
            System.out.println("Received: " + input);

            try {
                processOperation(input);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void end(){

    }

    private void processOperation(String input){

        OperationTypes opt = OperationTypes.fromString(input.trim());

        switch (opt){
            case USER_CREATE :
                System.out.println("Enter login for new user: ");
                String loginInput = scanner.nextLine();
                User user = userService.createUser(loginInput);
                System.out.println("User created: " + user);
                break;

            case SHOW_ALL_USERS :
                List<User> users = userService.getAllUsers();
                System.out.println("List of all users:");
                users.forEach(System.out::println);
                break;

            case ACCOUNT_CREATE :
                System.out.println("Enter the user id for which to create an account: ");
                Long userId = Long.parseLong(scanner.nextLine());
                User currentUser = userService.getUserById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("No such user with id = %d".formatted(userId)));
                var account = accountService.createAccount(currentUser);

                currentUser.getAccountList().add(account);

                System.out.printf("New account created with ID: %d for user: %s", account.getId(), currentUser.getLogin());
                break;

            case ACCOUNT_CLOSE :
                System.out.println("Enter account ID to close:");
                Long accId = Long.parseLong(scanner.nextLine());
                accountService.closeAccount(accId, userService);
                System.out.printf("Account with ID %d has been closed.", accId);
                break;

            case ACCOUNT_DEPOSIT :
                System.out.println("Enter account ID: ");
                long accountToDeposit = Long.parseLong(scanner.nextLine());
                System.out.println("Enter amount to deposit: ");
                int moneyToDeposit = Integer.parseInt(scanner.nextLine());
                accountService.moneyDeposit(accountToDeposit,moneyToDeposit);
                System.out.printf("Amount %d deposit to account ID %d.", moneyToDeposit, accountToDeposit);
                break;

            case ACCOUNT_TRANSFER :
                System.out.println("Enter source account ID: ");
                Long accountIdFrom = Long.parseLong(scanner.nextLine());
                System.out.println("Enter target account ID: ");
                Long accountIdTo = Long.parseLong(scanner.nextLine());
                System.out.println("Enter amount to transfer: ");
                int moneyToTrasfer = Integer.parseInt(scanner.nextLine());

                accountService.moneyTransfer(accountIdFrom, accountIdTo, moneyToTrasfer);
                System.out.printf("Amount %d transferred from account ID %d to account ID  %d.", moneyToTrasfer, accountIdFrom, accountIdTo);
                break;

            case ACCOUNT_WITHDRAW :
                System.out.println("Enter account ID to withdraw from: ");
                Long accountWithdraw = Long.parseLong(scanner.nextLine());
                System.out.println("Enter amount to withdraw: ");
                int moneyToWithdraw = Integer.parseInt(scanner.nextLine());
                accountService.moneyWithDraw(accountWithdraw,moneyToWithdraw);
                System.out.printf("Amount %d withdraw from account ID %d.", moneyToWithdraw, accountWithdraw);
                break;

            case UNKNOWN :
                System.err.println("Unknown command: " + input);
                break;
        }
    }
}
