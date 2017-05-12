package com.example.louisompusunggu.demospinner.model;

import java.math.BigDecimal;

/**
 * Created by louis.ompusunggu on 5/12/2017.
 */
public class Rekening {
    private String userId;
    private String type;
    private BigDecimal balance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
