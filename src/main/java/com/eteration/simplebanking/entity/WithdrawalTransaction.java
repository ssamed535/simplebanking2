package com.eteration.simplebanking.entity;

import com.eteration.simplebanking.enums.ExceptionMessage;
import com.eteration.simplebanking.exception.InsufficientBalanceException;

import javax.persistence.Entity;

@Entity
public class WithdrawalTransaction extends Transaction {
    @Override
    public String getType() {
        return "WithdrawalTransaction";
    }

    @Override
    public Double getNewBalance(Double currentBalance) {
        Double amount = getAmount();
        if (currentBalance < amount)
            throw new InsufficientBalanceException(ExceptionMessage.INSUFFICIENT_BALANCE.getMessage());
        Double newBalance = currentBalance - amount;
        return newBalance;
    }
}
