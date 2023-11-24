package ru.netology.moneytransferservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationData {

    private String operationId;
    // проверочный код
    private String code;

    @Override
    public String toString() {
        return "ConfirmationData{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
