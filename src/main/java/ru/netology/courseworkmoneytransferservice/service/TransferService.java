package ru.netology.courseworkmoneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.courseworkmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.netology.courseworkmoneytransferservice.exception.InvalidDataException;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.repository.TransferRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public String transfer(Transaction transaction) {

        validation(transaction);

        return transferRepository.addTransfer(transaction);
    }

    public String confirmOperation(ConfirmOperation operation) {
        validation(operation);
        Transaction transaction;
        if (operation.getCode().equals("OK")) {
            transaction = transferRepository.confirmOperation(operation.getOperationId());
        } else {
            transaction = transferRepository.errorConfirmOperation(operation.getOperationId());
        }
        if (transaction == null) {
            throw new ErrorTransferOrConfirmException("Error confirm : " + operation);
        }
        return operation.getOperationId();
    }

    private void validation(Transaction transaction) {
        if (transaction.getCardFromNumber() == null || transaction.getCardFromNumber().isEmpty()) {
            throw new InvalidDataException("Invalid CardFromNumber");
        }
        if (transaction.getCardToNumber() == null || transaction.getCardToNumber().isEmpty()) {
            throw new InvalidDataException("Invalid CardToNumber");
        }
        if (transaction.getCardFromValidTill() == null || transaction.getCardFromValidTill().isEmpty()) {
            throw new InvalidDataException("Invalid CardFromValidTill");
        }
        if (transaction.getCardFromCVV() == null || transaction.getCardFromCVV().isEmpty()) {
            throw new InvalidDataException("Invalid CardFromCVV");
        }
        if (transaction.getAmount().currency() == null) {
            throw new InvalidDataException("Invalid Currency");
        }
        if (transaction.getAmount().value() == 0) {
            throw new InvalidDataException("Invalid Amount");
        }
    }

    private void validation(ConfirmOperation operation) {
        if (operation.getCode() == null || operation.getCode().length() == 0) {
            throw new InvalidDataException("Invalid code");
        }
        if (operation.getOperationId() == null || operation.getOperationId().length() == 0) {
            throw new InvalidDataException("Invalid OperationId ");
        }
    }
}

