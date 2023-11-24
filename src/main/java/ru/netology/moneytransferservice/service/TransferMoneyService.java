package ru.netology.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.repository.TransferMoneyRepository;

/**
 *Аннотация @Service в Spring Framework указывает, что класс является сервисом, который предоставляет бизнес-логику
 * или другую функциональность для приложения. Класс, помеченный аннотацией @Service, обычно используется вместе с
 * аннотацией @Autowired для инъекции зависимостей в другие классы.
 *
 * Когда класс помечен аннотацией @Service, Spring автоматически создает экземпляр этого класса и управляет его
 * жизненным циклом. Это позволяет использовать сервис в других частях приложения без необходимости создавать
 * экземпляр самостоятельно.
 */

@Service
public class TransferMoneyService {

    private final TransferMoneyRepository transferMoneyRepository;

    public TransferMoneyService(TransferMoneyRepository transferMoneyRepository) {
        this.transferMoneyRepository = transferMoneyRepository;
    }
}
