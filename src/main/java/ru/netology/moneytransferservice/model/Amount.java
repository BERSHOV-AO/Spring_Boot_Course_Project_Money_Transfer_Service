package ru.netology.moneytransferservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * @Positive аннотация указывает, что значение поля "value" должно быть положительным числом.
 * Если значение не является положительным, то будет сгенерировано сообщение об ошибке, указанное в параметре "message".
 */

@Getter
@Setter
public class Amount {

    @Positive(message = "The amount cannot be zero or negative")
    private Integer value;

    // валюта
    @NotBlank(message = "Enter currency")
    private String currency;

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}