package com.eteration.simplebanking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
public abstract class Transaction {
    @Id
    @SequenceGenerator(name = "Transaction", sequenceName = "TRANSACTION_ID_SEQ")
    @GeneratedValue(generator = "Transaction")
    @JsonIgnore
    private Long id;
    private Date date = new Date();
    private Double amount = 0.0;
    private String approvalCode;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public abstract String getType();

    public abstract Double getNewBalance(Double currentBalance);
}
