package ru.netology.moneytransferservice.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model_dto.ConfirmationData;
import ru.netology.moneytransferservice.model_dto.OperationStatus;
import ru.netology.moneytransferservice.model_dto.TransferMoneyData;
import ru.netology.moneytransferservice.service.TransferMoneyService;

/**
 * @CrossOrigin - это аннотация в Java, которая позволяет настроить кросс-доменные запросы для RESTful веб-сервисов.
 * Когда веб-приложение работает на одном домене, оно может быть ограничено политиками безопасности браузера и не
 * может делать запросы на другие домены.
 * Аннотация @CrossOrigin позволяет обойти эти ограничения и разрешает запросыс других доменов.
 * @RestController аннотация в Spring Framework указывает, что класс является контроллером, который обрабатывает
 * HTTP-запросы и возвращает данные в формате, который определяется аннотациями @RequestMapping и другими аннотациями
 * методов контроллера.
 * <p>
 * Когда класс помечен аннотацией @RestController, Spring автоматически создает экземпляр этого класса и обрабатывает
 * входящие HTTP-запросы, вызывая соответствующие методы контроллера и возвращая результаты в формате,
 * определенном методом контроллера (например, JSON или XML).
 * @CrossOrigin аннотация с параметром origins = "*" разрешает запросы с любого источника.
 * @Validated аннотация указывает на то, что аргумент transferMoneyData метода transfer должен быть прошедшим валидацию.
 * В данном случае, аргумент transferMoneyData является объектом, полученным из тела HTTP-запроса (@RequestBody).
 * Аннотация @RequestBody указывает на то, что аргумент transferMoneyData должен быть преобразован
 * из тела HTTP-запроса в объект. Это позволяет автоматически преобразовывать JSON-данные, содержащиеся в теле запроса,
 * в объект TransferMoneyData.
 * Таким образом, эти аннотации вместе гарантируют, что данные, переданные в теле HTTP-запроса,
 * будут проходить валидацию перед выполнением метода transfer. Если данные не проходят валидацию,
 * то будет выброшено исключение MethodArgumentNotValidException, которое будет обработано в обработчике исключений.
 * <p>
 * Метод confirmOperation является обработчиком HTTP POST-запроса на путь "/confirmOperation".
 * Он принимает в качестве аргумента объект ConfirmationData, который будет преобразован
 * из тела HTTP-запроса в JSON-формате.
 * Аннотация @RequestBody указывает на то, что аргумент confirmationData должен быть преобразован
 * из тела HTTP-запроса в объект. Это позволяет автоматически преобразовывать JSON-данные,
 * содержащиеся в теле запроса, в объект ConfirmationData.
 * Метод confirmOperation вызывает метод confirm из сервиса transferMoneyService, передавая ему объект confirmationData.
 * Возвращаемое значение метода confirm будет возвращено как ответ на HTTP-запрос.
 * Если в процессе выполнения метода confirmOperation возникает исключение типа ErrorInputData,
 * оно будет выброшено и может быть обработано в обработчике исключений для возврата соответствующего
 * HTTP-статуса ошибки.
 */

@CrossOrigin(origins = "https://serp-ya.github.io/")
@RestController
public class TransferMoneyController {

    private final TransferMoneyService transferMoneyService;

    public TransferMoneyController(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }

    @PostMapping("/transfer")
    public void transfer(@Validated @RequestBody TransferMoneyData transferMoneyData) {
        transferMoneyService.transfer(transferMoneyData);
    }

    // подтверждение операции
    @PostMapping("/confirmOperation")
    public OperationStatus confirmOperation(@RequestBody ConfirmationData confirmationData) throws ErrorInputData {
        return transferMoneyService.confirm(confirmationData);
    }
}
