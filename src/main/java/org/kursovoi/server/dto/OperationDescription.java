package org.kursovoi.server.dto;

public enum OperationDescription {

    NEW_ACCOUNT("Создан нвоый счет"),
    NEW_LOAN_ORDER("Создана заявка на кредит"),
    NEW_DEPOSIT_ORDER("Создана заявка на вклад"),
    MAKE_TRANSACTION("Транзакция совершена");

    private final String message;

    OperationDescription(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
