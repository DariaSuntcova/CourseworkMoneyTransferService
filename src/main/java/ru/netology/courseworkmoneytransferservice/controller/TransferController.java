package ru.netology.courseworkmoneytransferservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.courseworkmoneytransferservice.model.ConfirmOperation;
import ru.netology.courseworkmoneytransferservice.model.Transaction;
import ru.netology.courseworkmoneytransferservice.service.TransferService;

@RestController
@RequestMapping("/")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody Transaction transaction) {
        return transferService.transfer(transaction);
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(@RequestBody ConfirmOperation operation){
        return transferService.confirmOperation(operation);
    }

}
