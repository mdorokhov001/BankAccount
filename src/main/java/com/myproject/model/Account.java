package com.myproject.model;

public class Account {
    final Long id;
    final Long userId;
    double moneyAmount;

    public Account(Long id, Long userId, int moneyAmount){
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount){
        this.moneyAmount = moneyAmount;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
