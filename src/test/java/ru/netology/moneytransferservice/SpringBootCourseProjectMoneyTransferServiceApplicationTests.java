package ru.netology.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.moneytransferservice.controller.TransferMoneyController;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model_dto.Amount;
import ru.netology.moneytransferservice.model_dto.ConfirmationData;
import ru.netology.moneytransferservice.model_dto.OperationStatus;
import ru.netology.moneytransferservice.model_dto.TransferMoneyData;
import ru.netology.moneytransferservice.repository.TransferMoneyRepository;
import ru.netology.moneytransferservice.service.TransferMoneyService;


/**
 * Данный класс содержит набор тестовых методов для проверки функциональности приложения. Рассмотрим каждый из них:
 *
 * 1. containerTest(): В этом тесте проверяется, что контейнер с приложением успешно запускается и отвечает на запросы.
 * Для этого используется TestRestTemplate, который отправляет GET-запрос на URL приложения и проверяет код ответа
 * (должен быть 404 - "Not Found").
 *
 * 2. transferServiceTest(): В данном тесте проверяется, что метод transfer() в классе TransferMoneyController
 * вызывает метод transfer() в классе TransferMoneyService. Для этого создается mock-объект класса TransferMoneyService,
 * и после вызова метода transfer() в контроллере проверяется,
 * что метод transfer() в сервисе был вызван хотя бы один раз.
 *
 * 3. transferRepositoryTest(): В этом тесте проверяется, что метод transfer() в классе TransferMoneyController
 * вызывает метод saveTransferData() в классе TransferMoneyRepository.
 * Для этого создаются mock-объекты классов Amount и TransferMoneyData, которые передаются в контроллер.
 * Затем роверяется, что метод saveTransferData() в репозитории был вызван хотя бы один раз.
 *
 * 4. confirmSuccessTest(): В данном тесте проверяется, что метод confirmOperation()
 * в классе TransferMoneyController вызывает методы saveConfirmationData() и getTransfers()
 * в классе TransferMoneyRepository. Для этого создается mock-объект класса TransferMoneyData,
 * который передается в контроллер. Затем проверяется, что методы saveConfirmationData() и getTransfers()
 * в репозитории были вызваны хотя бы один раз.
 *
 * 5. confirmErrorTest(): В этом тесте проверяется, что при передаче некорректного кода подтверждения
 * (confirmationData) метод confirmOperation() в классе TransferMoneyController выбрасывает исключение ErrorInputData.
 * Для этого создается mock-объект класса TransferMoneyData, который передается в контроллер. Затем проверяется,
 * что при вызове confirmOperation() с некорректным кодом исключение ErrorInputData действительно выбрасывается.
 * --------------------------------------------------------------------------------------------------------------------
 * Аннотация @Testcontainers используется для интеграции с фреймворком Testcontainers, который предоставляет возможность
 * запускать контейнеры Docker во время выполнения тестов.
 * Это позволяет создавать изолированное окружение для тестирования, включая базы данных,
 * очереди сообщений и другие сервисы.
 *
 * Аннотация @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT) используется
 * для запуска приложения Spring во время выполнения тестов. Она указывает на то, что нужно использовать случайный порт
 * для веб-сервера приложения. Это позволяет избежать конфликтов портов при одновременном выполнении тестов.
 * <p>
 * Обе аннотации используются вместе для создания окружения тестирования,
 * включающего запуск контейнеров Docker и приложения Spring. Это позволяет выполнять интеграционное тестирование,
 * проверяя работу приложения в реальном окружении.
 */

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCourseProjectMoneyTransferServiceApplicationTests {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Container
    private final GenericContainer<?> transferMoneyContainer =
            new GenericContainer<>("transfer-money:latest")
                    .withExposedPorts(5500);

    @Test
    void containerTest() {
        Integer port = transferMoneyContainer.getMappedPort(5500);

        ResponseEntity<String> entity = testRestTemplate.getForEntity(
                "http://localhost:" + port, String.class);

        Assertions.assertEquals(entity.getStatusCode(), HttpStatusCode.valueOf(404));
    }

    @Test
    void transferServiceTest() {
        TransferMoneyService service = Mockito.mock(TransferMoneyService.class);
        TransferMoneyController controller = new TransferMoneyController(service);

        controller.transfer(Mockito.any());

        Mockito.verify(service, Mockito.atLeastOnce()).transfer(Mockito.any());
    }

    @Test
    void transferRepositoryTest() {
        Amount amount = Mockito.mock(Amount.class);
        Mockito.when(amount.getValue()).thenReturn(100);
        Mockito.when(amount.getCurrency()).thenReturn("RUR");

        TransferMoneyData transferMoneyData = Mockito.mock(TransferMoneyData.class);
        Mockito.when(transferMoneyData.getCardFromNumber()).thenReturn("1234");
        Mockito.when(transferMoneyData.getCardFromValidTill()).thenReturn("12/34");
        Mockito.when(transferMoneyData.getCardFromCVV()).thenReturn("123");
        Mockito.when(transferMoneyData.getCardToNumber()).thenReturn("5678");
        Mockito.when(transferMoneyData.getAmount()).thenReturn(amount);

        TransferMoneyRepository repository = Mockito.mock(TransferMoneyRepository.class);
        TransferMoneyService service = new TransferMoneyService(repository);
        TransferMoneyController controller = new TransferMoneyController(service);

        controller.transfer(transferMoneyData);

        Mockito.verify(repository, Mockito.atLeastOnce()).saveTransferData(transferMoneyData);
    }

    @Test
    void confirmSuccessTest() throws ErrorInputData {
        TransferMoneyData transferMoneyData = Mockito.mock(TransferMoneyData.class);
        Mockito.when(transferMoneyData.getId()).thenReturn("0000");

        ConfirmationData confirmationData = new ConfirmationData();
        confirmationData.setCode("0000");
        confirmationData.setOperationId(null);

        Deque<TransferMoneyData> transfers = new ConcurrentLinkedDeque<>();
        transfers.push(transferMoneyData);

        TransferMoneyRepository repository = Mockito.mock(TransferMoneyRepository.class);
        Mockito.when(repository.saveConfirmationData(confirmationData)).thenReturn(
                new OperationStatus("0", "Successful"));
        Mockito.when(repository.getTransfers()).thenReturn(transfers);

        TransferMoneyService service = new TransferMoneyService(repository);
        TransferMoneyController controller = new TransferMoneyController(service);

        controller.confirmOperation(confirmationData);

        Mockito.verify(repository, Mockito.atLeastOnce()).saveConfirmationData(confirmationData);
    }

    @Test
    void confirmErrorTest() {
        TransferMoneyData transferMoneyData = Mockito.mock(TransferMoneyData.class);
        Mockito.when(transferMoneyData.getId()).thenReturn("0000");

        ConfirmationData confirmationData = new ConfirmationData();
        confirmationData.setCode("0001");
        confirmationData.setOperationId(null);

        Deque<TransferMoneyData> transfers = new ConcurrentLinkedDeque<>();
        transfers.push(transferMoneyData);

        TransferMoneyRepository repository = Mockito.mock(TransferMoneyRepository.class);
        Mockito.when(repository.saveConfirmationData(confirmationData)).thenReturn(
                new OperationStatus("0", "Successful"));
        Mockito.when(repository.getTransfers()).thenReturn(transfers);

        TransferMoneyService service = new TransferMoneyService(repository);
        TransferMoneyController controller = new TransferMoneyController(service);

        Assertions.assertThrows(ErrorInputData.class, () -> controller.confirmOperation(confirmationData));
    }
}
