package ru.netology.courseworkmoneytransferservice.exception;

public class ErrorTransferOrConfirmException extends RuntimeException {
    public ErrorTransferOrConfirmException(String message) {
        super(message);
    }
}
