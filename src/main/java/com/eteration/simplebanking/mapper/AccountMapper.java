package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.AccountResponseDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponseDto convertToAccountResponseDto(Account account);
    Account convertToAccount(AccountSaveRequestDto accountSaveRequestDto);
}
