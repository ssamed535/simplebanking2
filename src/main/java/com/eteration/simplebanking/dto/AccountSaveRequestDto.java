package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountSaveRequestDto {
    @NotEmpty
    private String owner;

    @NotEmpty
    private String accountNumber;

    @NotNull
    @Min(0)
    private Double balance;
}
