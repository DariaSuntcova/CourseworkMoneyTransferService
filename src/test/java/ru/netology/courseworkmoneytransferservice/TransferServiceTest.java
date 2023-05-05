package ru.netology.courseworkmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.courseworkmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.netology.courseworkmoneytransferservice.exception.InvalidDataException;
import ru.netology.courseworkmoneytransferservice.model.Amount;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.OperationResponse;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.repository.TransferRepository;
import ru.netology.courseworkmoneytransferservice.service.TransferService;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static ru.netology.courseworkmoneytransferservice.model.Currency.EUR;

@SpringBootTest
public class TransferServiceTest {
    Transaction transaction;
    ConfirmOperation operation;
    TransferService service;
    @Mock
    TransferRepository repository;

    @BeforeEach
    public void setUp() {
        service = new TransferService(repository);
        transaction = new Transaction("1234567890111213", "01/25",
                "456", "2222222222222222", new Amount(EUR, 2000));
        operation = new ConfirmOperation("1", "0000");
    }

    @Test
    public void transferValidationTest() {
        when(repository.addTransfer(transaction)).thenReturn("1");
        Assertions.assertEquals(new OperationResponse("1"), service.transfer(transaction));
    }

    @ParameterizedTest
    @MethodSource("argumentsTransferError")
    public void transferErrorValidationTest(Transaction transaction) {
        Assertions.assertThrows(InvalidDataException.class, () -> service.transfer(transaction));
    }

    @Test
    public void confirmOperationTest() {
        when(repository.confirmOperation("1")).thenReturn(transaction);
        Assertions.assertEquals(new OperationResponse("1"), service.confirmOperation(operation));
    }

    @ParameterizedTest
    @MethodSource("argumentsConfirmOperationError")
    public void confirmOperationErrorValidationTest(ConfirmOperation operation) {
        Assertions.assertThrows(InvalidDataException.class, () -> service.confirmOperation(operation));
    }

    @Test
    public void confirmOperationNullTransaction() {
        when(repository.confirmOperation("1")).thenReturn(null);
        Assertions.assertThrows(ErrorTransferOrConfirmException.class, () -> service.confirmOperation(operation));
    }

    public static Stream<Arguments> argumentsConfirmOperationError() {
        return Stream.of(
                Arguments.of(new ConfirmOperation(null, "4569")),
                Arguments.of(new ConfirmOperation("1", null))
        );
    }


    public static Stream<Arguments> argumentsTransferError() {
        return Stream.of(
                Arguments.of(new Transaction(null, "01/25",
                        "456", "2222222222222222", new Amount(EUR, 2000))),
                Arguments.of(new Transaction("1234567890111213", null,
                        "456", "2222222222222222", new Amount(EUR, 2000))),
                Arguments.of(new Transaction("1234567890111213", "01/25",
                        null, "2222222222222222", new Amount(EUR, 2000))),
                Arguments.of(new Transaction("1234567890111213", "01/25",
                        "456", null, new Amount(EUR, 2000))),
                Arguments.of(new Transaction("1234567890111213", "01/25",
                        "456", "2222222222222222", new Amount(null, 2000))),
                Arguments.of(new Transaction("1234567890111213", "01/25",
                        "456", "2222222222222222", new Amount(EUR, 0)))
        );
    }


}
