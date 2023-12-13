package ru.netology.moneytransferservice.controller;

import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model.ConfirmationData;
import ru.netology.moneytransferservice.model.OperationStatus;
import ru.netology.moneytransferservice.model.TransferMoneyData;

public interface TransferMoneyControllerInterface {
    OperationStatus transfer(TransferMoneyData transferMoneyData) throws ErrorInputData;

    OperationStatus confirmOperation(ConfirmationData confirmationData) throws ErrorInputData;
}
