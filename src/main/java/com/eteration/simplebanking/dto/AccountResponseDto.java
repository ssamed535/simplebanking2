package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class AccountResponseDto {
    private String owner;
    private String accountNumber;
    private Double balance;
    private Date createDate;
    private Set<Transaction> transactions;
}
