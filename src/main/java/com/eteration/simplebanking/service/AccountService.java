package com.eteration.simplebanking.service;

import com.eteration.simplebanking.dto.AccountResponseDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.dto.TransactionStatus;
import com.eteration.simplebanking.entity.Account;
import com.eteration.simplebanking.entity.Transaction;
import com.eteration.simplebanking.enums.ExceptionMessage;
import com.eteration.simplebanking.exception.ResourceNotFoundException;
import com.eteration.simplebanking.mapper.AccountMapper;
import com.eteration.simplebanking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountResponseDto findAccount(String accountNumber) {
        Account account = findAccountByAccountNumberWithControl(accountNumber);
        AccountResponseDto accountResponseDto = AccountMapper.INSTANCE.convertToAccountResponseDto(account);
        return accountResponseDto;
    }

    public AccountResponseDto saveAccount(AccountSaveRequestDto accountSaveRequestDto) {
        Account account = AccountMapper.INSTANCE.convertToAccount(accountSaveRequestDto);
        account = accountRepository.save(account);
        AccountResponseDto accountResponseDto = AccountMapper.INSTANCE.convertToAccountResponseDto(account);
        return accountResponseDto;
    }

    public TransactionStatus post(String accountNumber, Transaction transaction) {
        Account account = findAccountByAccountNumberWithControl(accountNumber);
        String approvalCode = getApprovalCodeAndUpdateAccountAfterTransaction(account, transaction);

        TransactionStatus transactionStatus = new TransactionStatus("OK", approvalCode);
        return transactionStatus;
    }

    public Account findAccountByAccountNumberWithControl(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND.getMessage());
        return account;
    }

    private String getApprovalCodeAndUpdateAccountAfterTransaction(Account account, Transaction transaction) {
        Double balance = account.getBalance();
        Set<Transaction> transactionSet = account.getTransactions();

        Double newBalance = transaction.getNewBalance(balance);
        account.setBalance(newBalance);

        String approvalCode = transactionService.generateApprovalCode();
        transaction.setApprovalCode(approvalCode);
        transactionSet.add(transaction);
        account.setTransactions(transactionSet);

        accountRepository.save(account);
        return approvalCode;
    }
}
