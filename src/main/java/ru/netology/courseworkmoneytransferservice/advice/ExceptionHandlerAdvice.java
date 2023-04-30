package ru.netology.courseworkmoneytransferservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.courseworkmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.netology.courseworkmoneytransferservice.exception.InvalidDataException;
import ru.netology.courseworkmoneytransferservice.logger.Logger;
import ru.netology.courseworkmoneytransferservice.logger.LoggerImpl;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger logger;

    public ExceptionHandlerAdvice() {
        logger = LoggerImpl.getInstance();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidDataException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorTransferOrConfirmException.class)
    public ResponseEntity<String> invalidCredentialsHandler(ErrorTransferOrConfirmException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
