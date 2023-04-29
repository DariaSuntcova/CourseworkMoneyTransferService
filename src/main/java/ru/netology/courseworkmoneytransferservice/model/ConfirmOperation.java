package ru.netology.courseworkmoneytransferservice.model;

import lombok.Getter;

public class ConfirmOperation {
    @Getter
    private final String operationId;
    @Getter
    private final String code;

    public ConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
