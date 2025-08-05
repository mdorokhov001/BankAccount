package com.myproject.service;

import com.myproject.model.Account;
import com.myproject.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {

    private Map<Long, Account> accountMap;
    private final AtomicLong nextId = new AtomicLong(1);

    private final double transferCommission;
    private final int accountAmount;

    public AccountService(int accountAmount, double transferCommission) {
        this.transferCommission = transferCommission;
        this.accountAmount = accountAmount;
        this.accountMap = new HashMap<>();
    }
    public Account createAccount(User user){
        long id = nextId.getAndIncrement();
        Account account = new Account(id, user.getId(), accountAmount);
        accountMap.put(id, account);
        return account;
    }

    public void closeAccount(Long accountId, UserService userService){

        Account accountToClose = getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("No such account with id = %d"
                        .formatted(accountId)));

        User user = userService.getUserById(accountToClose.getUserId()).orElseThrow(() -> new IllegalArgumentException("No such user with id =%d"
                .formatted(accountToClose.getUserId())));

        List<Account> accountList = getAllUserAccounts(accountToClose.getUserId());

        if (accountList.size() == 1){
            throw new IllegalArgumentException("Account cannot be closed");
        }

        Account accountToTransfer = accountList.stream()
                        .filter(acc -> acc.getId() != accountId)
                                .findFirst()
                                        .orElseThrow();

        accountToTransfer.setMoneyAmount(accountToTransfer.getMoneyAmount() + accountToClose.getMoneyAmount());
        accountMap.remove(accountId);
        user.getAccountList().remove(accountToClose);
    }

    public void moneyDeposit(Long accountId, double moneyToDeposit){
        Account accountFrom = getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("No such account with id = %d".formatted(accountId)));

        if (moneyToDeposit <= 0){
            throw new IllegalArgumentException("Deposit cannot be less then 0, current value = %f".formatted(moneyToDeposit));
        }

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() + moneyToDeposit);
    }

    public void moneyTransfer(Long accountIdFrom, Long accountIdTo, double moneyToTrasfer){
        Account accountFrom = getAccountById(accountIdFrom)
                .orElseThrow(() -> new IllegalArgumentException("No such account with id = %d".formatted(accountIdFrom)));

        Account accountTo = getAccountById(accountIdTo)
                .orElseThrow(() -> new IllegalArgumentException("No such account with id = %d".formatted(accountIdTo)));

        if (moneyToTrasfer <= 0){
            throw new IllegalArgumentException("Transfer cannot be less then 0, current value = %f"
                    .formatted(moneyToTrasfer));
        }

        int totalMoney = (int) (accountFrom.getUserId() != accountTo.getUserId()
                        ? moneyToTrasfer * (1 - transferCommission)
                        : moneyToTrasfer);

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() - moneyToTrasfer);
        accountTo.setMoneyAmount(accountTo.getMoneyAmount() + totalMoney);

    }

    public void moneyWithDraw(Long accountId, double moneyToWithDraw){
        Account accountFrom = getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("No such account with id = %d".formatted(accountId)));

        if (moneyToWithDraw <= 0){
            throw new IllegalArgumentException("Withdraw cannot be less then 0, current value = %f"
                    .formatted(moneyToWithDraw));
        }

        if (accountFrom.getMoneyAmount() < moneyToWithDraw){
            throw new IllegalArgumentException("No such money to withdraw\n" +
                    "from account: id=%d, moneyAmount=%f, attemptedWithdraw=%f"
                            .formatted(accountId, accountFrom.getMoneyAmount(), moneyToWithDraw));
        }

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-moneyToWithDraw);
    }

    public List<Account> getAllUserAccounts(Long userId){
        return accountMap.values()
                .stream()
                .filter(acc -> acc.getUserId() == userId)
                .toList();
    }

    public Optional<Account> getAccountById(Long accountId){
        return Optional.ofNullable(accountMap.get(accountId));
    }
}
