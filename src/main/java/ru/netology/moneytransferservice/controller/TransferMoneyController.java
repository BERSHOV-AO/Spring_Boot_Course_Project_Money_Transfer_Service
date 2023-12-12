package ru.netology.moneytransferservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model.ConfirmationData;
import ru.netology.moneytransferservice.model.OperationStatus;
import ru.netology.moneytransferservice.model.TransferMoneyData;
import ru.netology.moneytransferservice.service.TransferMoneyService;
import ru.netology.moneytransferservice.service.TransferMoneyServiceInterface;

@RequiredArgsConstructor
@CrossOrigin(origins = "https://serp-ya.github.io/")
@RestController
public class TransferMoneyController implements TransferMoneyControllerInterface {

    private final TransferMoneyServiceInterface transferMoneyService;

    @Override
    @PostMapping("/transfer")
    public OperationStatus transfer(@Validated @RequestBody TransferMoneyData transferMoneyData) {
        return transferMoneyService.transfer(transferMoneyData);
    }

    @Override
    @PostMapping("/confirmOperation")
    public OperationStatus confirmOperation(@RequestBody ConfirmationData confirmationData) throws ErrorInputData {
        return transferMoneyService.confirm(confirmationData);
    }
}
