package ru.netology.moneytransferservice.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.service.TransferMoneyService;

/**
 * @CrossOrigin - это аннотация в Java, которая позволяет настроить кросс-доменные запросы для RESTful веб-сервисов.
 * Когда веб-приложение работает на одном домене, оно может быть ограничено политиками безопасности браузера и не
 * может делать запросы на другие домены.
 * Аннотация @CrossOrigin позволяет обойти эти ограничения и разрешает запросыс других доменов.
 *
 * @RestController аннотация в Spring Framework указывает, что класс является контроллером, который обрабатывает
 * HTTP-запросы и возвращает данные в формате, который определяется аннотациями @RequestMapping и другими аннотациями
 * методов контроллера.
 *
 * Когда класс помечен аннотацией @RestController, Spring автоматически создает экземпляр этого класса и обрабатывает
 * входящие HTTP-запросы, вызывая соответствующие методы контроллера и возвращая результаты в формате,
 * определенном методом контроллера (например, JSON или XML).
 *
 * @CrossOrigin аннотация с параметром origins = "*" разрешает запросы с любого источника.
 */

@CrossOrigin(origins = "https://serp-ya.github.io/")
@RestController
public class TransferMoneyController {

    private final TransferMoneyService transferMoneyService;

    public TransferMoneyController(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }





}
