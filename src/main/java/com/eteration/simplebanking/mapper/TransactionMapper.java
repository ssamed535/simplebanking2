package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.BillPaymentTransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.entity.BillPaymentTransaction;
import com.eteration.simplebanking.entity.DepositTransaction;
import com.eteration.simplebanking.entity.WithdrawalTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    DepositTransaction convertToDepositTransaction(TransactionRequestDto transactionRequestDto);
    WithdrawalTransaction convertToWithdrawalTransaction(TransactionRequestDto transactionRequestDto);
    BillPaymentTransaction convertToBillPaymentTransaction(BillPaymentTransactionRequestDto billPaymentTransactionRequestDto);
}
