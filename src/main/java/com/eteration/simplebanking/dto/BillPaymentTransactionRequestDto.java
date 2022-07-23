package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BillPaymentTransactionRequestDto {

    @NotNull
    @Min(1)
    private Double amount;

    @NotEmpty
    private String payee;
}
