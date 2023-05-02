package ru.netology.courseworkmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.courseworkmoneytransferservice.controller.TransferController;
import ru.netology.courseworkmoneytransferservice.model.Amount;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.service.TransferService;

import static org.mockito.Mockito.when;
import static ru.netology.courseworkmoneytransferservice.model.Currency.EUR;

@SpringBootTest
public class TransferControllerTest {
    Transaction transaction;
    ConfirmOperation operation;
    @Mock
    TransferService service;

    TransferController controller;

    @BeforeEach
    public void setUp() {
        controller = new TransferController(service);
        transaction = new Transaction("1234567890111213", "01/25",
                "456", "2222222222222222", new Amount(EUR, 2000));
        operation = new ConfirmOperation("1", "0000");
    }

    @Test
    public void transferTest() {
        when(service.transfer(transaction)).thenReturn("1");
        Assertions.assertEquals("1", controller.transfer(transaction));
    }

    @Test
    public void confirmOperationTest() {
        when(service.confirmOperation(operation)).thenReturn("1");
        Assertions.assertEquals("1", controller.confirmOperation(operation));
    }
}
