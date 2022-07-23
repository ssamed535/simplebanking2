package com.eteration.simplebanking.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BillPaymentTransaction extends WithdrawalTransaction {
    private String payee;

    @Override
    public String getType() {
        return "BillPaymentTransaction";
    }
}
