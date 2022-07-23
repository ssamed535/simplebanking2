package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.dto.BillPaymentTransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/accounts")
public interface AccountController {

    @GetMapping("/{accountNumber}")
    ResponseEntity getAccount(@PathVariable String accountNumber);

    @PostMapping()
    ResponseEntity save(@Valid @RequestBody AccountSaveRequestDto accountSaveRequestDto);

    @PostMapping("/credit/{accountNumber}")
    ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @Valid @RequestBody TransactionRequestDto transactionRequestDto);

    @PostMapping("/debit/{accountNumber}")
    ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @Valid @RequestBody TransactionRequestDto transactionRequestDto);

    @PostMapping("/bill-payment/{accountNumber}")
    ResponseEntity<TransactionStatus> billPayment(@PathVariable String accountNumber, @Valid @RequestBody BillPaymentTransactionRequestDto billPaymentTransactionRequestDto);
}
