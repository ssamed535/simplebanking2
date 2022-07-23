package com.eteration.simplebanking.enums;

import lombok.Getter;

public enum ExceptionMessage {
    INSUFFICIENT_BALANCE("Insufficient Balance"),
    RESOURCE_NOT_FOUND("Resource not found"),
    ;

    @Getter
    private String message;
    ExceptionMessage(String message) {
        this.message = message;
    }
}
