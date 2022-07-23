package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.*;
import com.eteration.simplebanking.entity.BillPaymentTransaction;
import com.eteration.simplebanking.entity.DepositTransaction;
import com.eteration.simplebanking.entity.WithdrawalTransaction;
import com.eteration.simplebanking.mapper.TransactionMapper;
import com.eteration.simplebanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public ResponseEntity getAccount(String accountNumber) {
        AccountResponseDto accountResponseDto = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(accountResponseDto);
    }

    @Override
    public ResponseEntity save(AccountSaveRequestDto accountSaveRequestDto) {
        AccountResponseDto accountResponseDto = accountService.saveAccount(accountSaveRequestDto);
        return ResponseEntity.ok(accountResponseDto);
    }

    @Override
    public ResponseEntity<TransactionStatus> credit(String accountNumber, TransactionRequestDto transactionRequestDto) {
        DepositTransaction depositTransaction = TransactionMapper.INSTANCE.convertToDepositTransaction(transactionRequestDto);
        TransactionStatus transactionStatus = accountService.post(accountNumber, depositTransaction);
        return ResponseEntity.ok(transactionStatus);
    }

    @Override
    public ResponseEntity<TransactionStatus> debit(String accountNumber, TransactionRequestDto transactionRequestDto) {
        WithdrawalTransaction withdrawalTransaction = TransactionMapper.INSTANCE.convertToWithdrawalTransaction(transactionRequestDto);
        TransactionStatus transactionStatus = accountService.post(accountNumber, withdrawalTransaction);
        return ResponseEntity.ok(transactionStatus);
    }

    @Override
    public ResponseEntity<TransactionStatus> billPayment(String accountNumber, BillPaymentTransactionRequestDto billPaymentTransactionRequestDto) {
        BillPaymentTransaction billPaymentTransaction = TransactionMapper.INSTANCE.convertToBillPaymentTransaction(billPaymentTransactionRequestDto);
        TransactionStatus transactionStatus = accountService.post(accountNumber, billPaymentTransaction);
        return ResponseEntity.ok(transactionStatus);
    }
}
