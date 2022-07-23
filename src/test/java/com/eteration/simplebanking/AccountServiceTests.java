package com.eteration.simplebanking;

import com.eteration.simplebanking.dto.AccountResponseDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.entity.Account;
import com.eteration.simplebanking.entity.DepositTransaction;
import com.eteration.simplebanking.entity.WithdrawalTransaction;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.service.AccountService;
import com.eteration.simplebanking.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTests {

    @Spy
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionService transactionService;

    private Account account;
    private DepositTransaction depositTransaction;
    private WithdrawalTransaction withdrawalTransaction;

    @BeforeEach
    void setUp() {
        String owner = "Demet Demircan";
        String accountNumber = "9834";

        account = new Account();
        account.setAccountNumber(accountNumber);
        account.setOwner(owner);

        depositTransaction = new DepositTransaction();
        withdrawalTransaction = new WithdrawalTransaction();
    }

    @Test
    void findAccount() {
        String accountNumber = "17892";
        Account account = mock(Account.class);

        when(account.getAccountNumber()).thenReturn(accountNumber);
        when(accountRepository.findByAccountNumber(anyString())).thenReturn(account);

        AccountResponseDto accountResponseDto = accountService.findAccount(accountNumber);
        assertEquals(accountNumber, accountResponseDto.getAccountNumber());

    }

    @Test
    public void testCreateAccountAndSetBalance0() {
        String owner = "Kerem Karaca";
        String accountNumber = "17892";

        AccountSaveRequestDto accountSaveRequestDto = mock(AccountSaveRequestDto.class);
        Account account = mock(Account.class);

        when(account.getOwner()).thenReturn(owner);
        when(account.getAccountNumber()).thenReturn(accountNumber);
        when(accountRepository.save(any())).thenReturn(account);

        AccountResponseDto accountResponseDto = accountService.saveAccount(accountSaveRequestDto);
        assertTrue(accountResponseDto.getOwner().equals(owner));
        assertTrue(accountResponseDto.getAccountNumber().equals(accountNumber));
        assertTrue(accountResponseDto.getBalance() == 0);
    }

    @Test
	public void testDepositIntoBankAccount() {

        depositTransaction.setAmount(100.0);

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
        accountService.post(account.getAccountNumber(), depositTransaction);
        assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() {

        depositTransaction.setAmount(100.0);
        withdrawalTransaction.setAmount(50.0);

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
        accountService.post(account.getAccountNumber(), depositTransaction);
		assertTrue(account.getBalance() == 100);
        accountService.post(account.getAccountNumber(), withdrawalTransaction);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		assertThrows( InsufficientBalanceException.class, () -> {

            depositTransaction.setAmount(100.0);
            withdrawalTransaction.setAmount(500.0);

            when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
            accountService.post(account.getAccountNumber(), depositTransaction);
            accountService.post(account.getAccountNumber(), withdrawalTransaction);
		  });

	}

	@Test
	public void testTransactions() throws InsufficientBalanceException {

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
		assertTrue(account.getTransactions().size() == 0);

        depositTransaction.setAmount(100.0);
		assertTrue(depositTransaction.getDate() != null);
        accountService.post(account.getAccountNumber(), depositTransaction);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getTransactions().size() == 1);

        withdrawalTransaction.setAmount(60.0);
		assertTrue(withdrawalTransaction.getDate() != null);
        accountService.post(account.getAccountNumber(), withdrawalTransaction);
		assertTrue(account.getBalance() == 40);
		assertTrue(account.getTransactions().size() == 2);
	}
}