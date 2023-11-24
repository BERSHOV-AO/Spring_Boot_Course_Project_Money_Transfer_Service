package ru.netology.moneytransferservice.model_dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * 1. Поле cardFromNumber является строковым полем, которое должно быть непустым и иметь длину 16 символов.
 * Оно представляет номер карты отправителя.
 * <p>
 * 2. Поле cardFromValidTill является строковым полем, которое должно быть непустым и соответствовать
 * формату MM/YY (месяц/год). Оно представляет срок действия карты отправителя.
 * <p>
 * 3. Поле cardFromCVV является строковым полем, которое должно быть непустым и иметь длину 3 символа.
 * Оно представляет CVV-код карты отправителя.
 * <p>
 * 4. Поле cardToNumber является строковым полем, которое должно быть непустым и иметь длину 16 символов.
 * Оно представляет номер карты получателя.
 * <p>
 * 5. Поле amount является объектом класса Amount, который содержит информацию о сумме перевода.
 * <p>
 * 6. Поле id является строковым полем, которое не имеет ограничений и может быть пустым.
 * Оно представляет идентификатор перевода.
 *
 * @NotBlank аннотация  указывает, что поле должно быть непустым. Если значение поля пустое
 * или содержит только пробелы, то будет сгенерировано сообщение об ошибке с текстом "Enter card number".
 * Эта аннотация обычно используется для валидации входных данных, чтобы проверить,
 * что обязательные поля не остались пустыми.
 * @Pattern аннотация  указывает, что поле должно соответствовать определенному регулярному выражению.
 * В данном случае, поле cardFromValidTill должно быть в формате "месяц/год" (например, "01/22")
 * иначе будет сгенерировано сообщение об ошибке с текстом "Incorrect date".
 * Эта аннотация также используется для валидации входных данных, чтобы проверить,
 * что значение поля соответствует определенному формату.
 * @Valid аннотация указывает, что поле должно быть проверено на валидность во время выполнения. В данном случае,
 * аннотация @Valid применяется к полю "amount", которое является объектом класса "Amount". Аннотация говорит о том,
 * что объект "Amount" должен быть проверен на валидность с использованием правил валидации,
 * определенных для этого объекта.
 */

@Getter
@Setter
public class TransferMoneyData {
    @NotBlank(message = "Enter card number")
    @Size(min = 16, max = 16, message = "Length of card number must be 16")
    private String cardFromNumber;

    @NotBlank(message = "Enter card expiration date")
    // Регулярное выражение ("(0[1-9]|1[0-2])" - месяц от "01" до "12") ("/([0-9]{2})" -  год в формате "00" до "99")
    @Pattern(regexp = "(0[1-9]|1[0-2])/([0-9]{2})", message = "Incorrect date")
    private String cardFromValidTill;

    @NotBlank(message = "Enter card cvv")
    @Size(min = 3, max = 3, message = "CVV's length must be 3")
    private String cardFromCVV;

    @NotBlank(message = "Enter card number")
    @Size(min = 16, max = 16, message = "Length of card number must be 16")
    private String cardToNumber;

    @Valid
    private Amount amount;
    private String id;

    @Override
    public String toString() {
        return "TransferMoneyData{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                '}';
    }
}
