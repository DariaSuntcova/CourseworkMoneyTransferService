package ru.netology.courseworkmoneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.model.TransactionState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepository {
    private final Map<String, Transaction> transferMap;
    private final AtomicInteger id;

    public TransferRepository() {
        id = new AtomicInteger(1);
        transferMap = new ConcurrentHashMap<>();
    }

    public String addTransfer(Transaction transaction) {
        String transactionId = id.getAndIncrement() + "";
        transferMap.put(transactionId, transaction);
        return transactionId;
    }

    public Transaction confirmOperation(String id) {
        if (!transferMap.containsKey(id)) {
            return null;
        }
        Transaction transaction = transferMap.get(id);
        transaction.setState(TransactionState.OK);
        return transaction;
    }

    public Transaction errorConfirmOperation(String id) {
        if (!transferMap.containsKey(id)) {
            return null;
        }
        Transaction transaction = transferMap.get(id);
        transaction.setState(TransactionState.ERROR);
        return transaction;
    }
}

