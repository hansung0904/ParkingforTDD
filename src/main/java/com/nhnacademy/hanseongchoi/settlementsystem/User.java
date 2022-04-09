package com.nhnacademy.hanseongchoi.settlementsystem;

public class User {
    private String userId;
    private Money amount;

    public User(String userId, Money amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public Money getAmount() {
        return amount;
    }
}
