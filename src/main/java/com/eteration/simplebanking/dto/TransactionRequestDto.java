package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionRequestDto {
    @NotNull
    @Min(1)
    private Double amount;
}
