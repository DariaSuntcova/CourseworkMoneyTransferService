package ru.netology.courseworkmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.courseworkmoneytransferservice.advice.ErrorResponse;
import ru.netology.courseworkmoneytransferservice.model.Amount;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.OperationResponse;
import ru.netology.courseworkmoneytransferservice.model.Transaction;

import java.util.Objects;

import static ru.netology.courseworkmoneytransferservice.model.Currency.EUR;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContainerTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private final GenericContainer<?> myApp = new GenericContainer<>("app:latest")
            .withExposedPorts(5500);

    private final ConfirmOperation operation = new ConfirmOperation("1", "0000");
    private final Transaction transaction = new Transaction("1234567890111213", "01/25",
            "456", "2222222222222222", new Amount(EUR, 2000));


    @Test
    public void myAppTransferTest() {
        OperationResponse response = restTemplate.postForObject("http://localhost:" + myApp.getMappedPort(5500)
                + "/transfer", transaction, OperationResponse.class);
        Assertions.assertEquals(new OperationResponse("1"), response);
    }

    @Test
    public void myAppTransferInvalidDataTest() {
        Transaction transaction = new Transaction(null, null, null,
                null, new Amount(null, 0));
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("http://localhost:"
                + myApp.getMappedPort(5500) + "/transfer", transaction, ErrorResponse.class);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).id(), 1);
    }

    @Test
    void appConfirmOperationTest() {
        restTemplate.postForObject("http://localhost:" + myApp.getMappedPort(5500)
                + "/transfer", transaction, OperationResponse.class);
        OperationResponse response = restTemplate.postForObject("http://localhost:"
                + myApp.getMappedPort(5500) + "/confirmOperation", operation, OperationResponse.class);
        Assertions.assertEquals(new OperationResponse("1"), response);
    }

}
