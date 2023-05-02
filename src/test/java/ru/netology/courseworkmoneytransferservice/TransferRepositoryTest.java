package ru.netology.courseworkmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.courseworkmoneytransferservice.model.Amount;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.model.TransactionState;
import ru.netology.courseworkmoneytransferservice.repository.TransferRepository;

import static ru.netology.courseworkmoneytransferservice.model.Currency.EUR;

@SpringBootTest
public class TransferRepositoryTest {
    TransferRepository repository;
    Transaction transaction;

    @BeforeEach
    public void setUp() {
        repository = new TransferRepository();
        transaction = new Transaction("1234567890111213", "01/25",
                "456", "2222222222222222", new Amount(EUR, 2000));
    }

    @Test
    public void addTransferTest() {
        Assertions.assertEquals("1", repository.addTransfer(transaction));
    }

    @Test
    public void confirmOperationTest() {
        repository.addTransfer(transaction);
        Assertions.assertEquals(transaction, repository.confirmOperation("1"));
        Assertions.assertEquals(TransactionState.OK, transaction.getState());
    }

    @Test
    public void confirmOperationTestNull() {
        Assertions.assertNull(repository.confirmOperation("1"));
    }

    @Test
    public void errorConfirmOperationTest() {
        repository.addTransfer(transaction);
        Assertions.assertEquals(transaction, repository.errorConfirmOperation("1"));
        Assertions.assertEquals(TransactionState.ERROR, transaction.getState());
    }

    @Test
    public void errorConfirmOperationTestNull() {
        Assertions.assertNull(repository.confirmOperation("1"));
    }

}
