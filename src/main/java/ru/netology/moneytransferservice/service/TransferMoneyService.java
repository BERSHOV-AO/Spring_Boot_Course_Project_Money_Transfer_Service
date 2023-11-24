package ru.netology.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.logger.Logger;
import ru.netology.moneytransferservice.model_dto.TransferMoneyData;
import ru.netology.moneytransferservice.repository.TransferMoneyRepository;

import java.util.UUID;

/**
 *Аннотация @Service в Spring Framework указывает, что класс является сервисом, который предоставляет бизнес-логику
 * или другую функциональность для приложения. Класс, помеченный аннотацией @Service, обычно используется вместе с
 * аннотацией @Autowired для инъекции зависимостей в другие классы.
 *
 * Когда класс помечен аннотацией @Service, Spring автоматически создает экземпляр этого класса и управляет его
 * жизненным циклом. Это позволяет использовать сервис в других частях приложения без необходимости создавать
 * экземпляр самостоятельно.
 *
 * UUID (Universally Unique Identifier) в Java представляет собой 128-битное значение,
 * которое используется для идентификации объектов и обеспечения их уникальности.
 * Он может быть использован для генерации уникальных идентификаторов для различных целей,
 * таких как идентификация записей в базе данных, создание временных файлов или идентификация сессий пользователей.
 */

@Service
public class TransferMoneyService {

    private final TransferMoneyRepository transferMoneyRepository;

    public TransferMoneyService(TransferMoneyRepository transferMoneyRepository) {
        this.transferMoneyRepository = transferMoneyRepository;
    }

    // передача
    public void transfer(TransferMoneyData transferMoneyData) {
        transferMoneyData.setId(String.valueOf(UUID.randomUUID()));
        Logger logger = new Logger("src/main/java/ru/netology/moneytransferservice/logs/logs.txt");
        logger.log()

    }






}
