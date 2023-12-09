package ru.netology.moneytransferservice.controller;

import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model.ConfirmationData;
import ru.netology.moneytransferservice.model.OperationStatus;
import ru.netology.moneytransferservice.model.TransferMoneyData;

public interface TransferMoneyControllerInterface {
    void transfer(TransferMoneyData transferMoneyData);
    OperationStatus confirmOperation(ConfirmationData confirmationData) throws ErrorInputData;
}