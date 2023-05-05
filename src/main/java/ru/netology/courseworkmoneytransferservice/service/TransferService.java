package ru.netology.courseworkmoneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.courseworkmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.netology.courseworkmoneytransferservice.exception.InvalidDataException;
import ru.netology.courseworkmoneytransferservice.logger.Logger;
import ru.netology.courseworkmoneytransferservice.logger.LoggerImpl;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.OperationResponse;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.repository.TransferRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private static Logger logger;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
        logger = LoggerImpl.getInstance();
    }

    public OperationResponse transfer(Transaction transaction) {
        validation(transaction);
        String id = transferRepository.addTransfer(transaction);
        logger.info(transaction.toString());
        return new OperationResponse(id);
    }

    public OperationResponse confirmOperation(ConfirmOperation operation) {
        validation(operation);
        Transaction transaction;
        if (operation.code().equals("0000")) {
            transaction = transferRepository.confirmOperation(operation.operationId());
        } else {
            transaction = transferRepository.errorConfirmOperation(operation.operationId());
        }
        if (transaction == null) {
            throw new ErrorTransferOrConfirmException("Error confirm : transaction with id  " + operation.operationId()
                    + " was not found" + operation);
        } else {
            logger.info(operation.toString());
            logger.info("Transaction state changed");
            logger.info(transaction.toString());
            return new OperationResponse(operation.operationId());
        }
    }

    private void validation(Transaction transaction) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex = "\\d+";
        boolean flag = true;
        if (transaction.getCardFromNumber() == null || transaction.getCardFromNumber().isEmpty()
                || !transaction.getCardFromNumber().matches(regex)) {
            flag = false;
            stringBuilder.append("Invalid CardFromNumber ");
        }
        if (transaction.getCardToNumber() == null || transaction.getCardToNumber().isEmpty()
                || !transaction.getCardToNumber().matches(regex)) {
            flag = false;
            stringBuilder.append("Invalid CardToNumber ");
        }
        if (transaction.getCardFromValidTill() == null || transaction.getCardFromValidTill().isEmpty()) {
            flag = false;
            stringBuilder.append("Invalid CardFromValidTill ");
        }
        if (transaction.getCardFromCVV() == null || transaction.getCardFromCVV().isEmpty()
                || !transaction.getCardFromCVV().matches(regex) || transaction.getCardFromCVV().length() != 3) {
            flag = false;
            stringBuilder.append("Invalid CardFromCVV ");
        }
        if (transaction.getAmount().currency() == null) {
            flag = false;
            stringBuilder.append("Invalid Currency ");
        }
        if (transaction.getAmount().value() <= 0) {
            flag = false;
            stringBuilder.append("Invalid Amount ");
        }
        if (!flag) {
            throw new InvalidDataException("Invalid transfer :" + stringBuilder);
        }
    }

    private void validation(ConfirmOperation operation) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        if (operation.code() == null || operation.code().length() == 0) {
            flag = false;
            stringBuilder.append("Invalid code ");
        }
        if (operation.operationId() == null || operation.operationId().length() == 0) {
            flag = false;
            stringBuilder.append("Invalid OperationId ");
        }
        if (!flag) {
            throw new InvalidDataException("Invalid confirm :" + stringBuilder);
        }
    }
}

