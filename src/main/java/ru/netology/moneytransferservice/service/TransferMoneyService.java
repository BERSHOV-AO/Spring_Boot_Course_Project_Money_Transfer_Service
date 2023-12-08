package ru.netology.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.logger.Logger;
import ru.netology.moneytransferservice.model.ConfirmationData;
import ru.netology.moneytransferservice.model.OperationStatus;
import ru.netology.moneytransferservice.model.TransferMoneyData;
import ru.netology.moneytransferservice.repository.TransferMoneyRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

//@Service
//public class TransferMoneyService {
//
//    private final TransferMoneyRepository transferMoneyRepository;
//
//    private final String LOGGER_PATH = "src/main/java/ru/netology/moneytransferservice/logs/logs.txt";
//
//    public TransferMoneyService(TransferMoneyRepository transferMoneyRepository) {
//        this.transferMoneyRepository = transferMoneyRepository;
//    }
//
//    // передача
//    public void transfer(TransferMoneyData transferMoneyData) {
//        transferMoneyData.setId(String.valueOf(UUID.randomUUID()));
//        Logger logger = new Logger(LOGGER_PATH);
//        logger.log("Date : " + LocalDateTime.now().format(
//                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n"
//                + "Card From : " + transferMoneyData.getCardFromNumber() + "\n"
//                + "Card To : " + transferMoneyData.getCardToNumber() + "\n"
//                + "Value : " + transferMoneyData.getAmount().getValue() / 100 + "\n"
//                + "Commission : 1%" + "\n"
//                + "ID : " + transferMoneyData.getId() + "\n");
//        transferMoneyRepository.saveTransferData(transferMoneyData);
//    }
//
//
//    // подтверждение
//    public OperationStatus confirm(ConfirmationData confirmationData) throws ErrorInputData {
//        TransferMoneyData transferMoneyData = transferMoneyRepository.getTransfers().pop();
//        String code = transferMoneyRepository.getOperations()
//                .getOrDefault(transferMoneyData.getId(), "0000");
//        if (confirmationData.getCode().equals(code)) {
//            return transferMoneyRepository.saveConfirmationData(confirmationData);
//        } else {
//            throw new ErrorInputData("Wrong verification code!");
//        }
//    }
//}


@Service
public class TransferMoneyService {

    private final String ZERO = "0000";

    private final TransferMoneyRepository transferMoneyRepository;

    private final String LOGGER_PATH = "src/main/java/ru/netology/moneytransferservice/logs.txt";

    public TransferMoneyService(TransferMoneyRepository transferMoneyRepository) {
        this.transferMoneyRepository = transferMoneyRepository;
    }

    // передача
    public void transfer(TransferMoneyData transferMoneyData) {

        transferMoneyData.setId(String.valueOf(UUID.randomUUID()));
        Logger logger = new Logger(LOGGER_PATH);
        logger.log("Date : " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n"
                + "Card From : " + transferMoneyData.getCardFromNumber() + "\n"
                + "Card To : " + transferMoneyData.getCardToNumber() + "\n"
                + "Value : " + transferMoneyData.getAmount().value() / 100 + "\n"
                + "Commission : 1%" + "\n"
                + "ID : " + transferMoneyData.getId() + "\n");
        transferMoneyRepository.saveTransferData(transferMoneyData);
    }


    // подтверждение
    public OperationStatus confirm(ConfirmationData confirmationData) throws ErrorInputData {
        TransferMoneyData transferMoneyData = transferMoneyRepository.getTransfers().pop();
        String code = transferMoneyRepository.getOperations()
                .getOrDefault(transferMoneyData.getId(), ZERO);
        if (confirmationData.code().equals(code)) {
            return transferMoneyRepository.saveConfirmationData(confirmationData);
        } else {
            throw new ErrorInputData("Wrong verification code!");
        }
    }
}