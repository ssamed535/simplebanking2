package com.eteration.simplebanking.entity;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {

    @Override
    public String getType() {
        return "DepositTransaction";
    }

    @Override
    public Double getNewBalance(Double currentBalance) {
        Double amount = getAmount();
        Double newBalance = currentBalance + amount;
        return newBalance;
    }
}
