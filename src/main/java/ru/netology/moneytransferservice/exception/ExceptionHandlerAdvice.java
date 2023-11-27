package ru.netology.moneytransferservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransferservice.logger.Logger;
import ru.netology.moneytransferservice.model_dto.OperationStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @RestControllerAdvice аннотация  в Spring Framework используется для создания глобального обработчика исключений
 * в REST контроллерах. Она объединяет в себе аннотации @ControllerAdvice и @ResponseBody,
 * что позволяет ей обрабатывать исключения и возвращать результаты в формате JSON или XML.
 * <p>
 * Когда происходит исключение в REST контроллере, аннотация @RestControllerAdvice позволяет определить методы,
 * которые будут обрабатывать это исключение. Эти методы должны быть аннотированы с помощью @ExceptionHandler
 * и указывать на тип исключения, которое они обрабатывают.
 * <p>
 * Таким образом, аннотация @RestControllerAdvice позволяет централизованно управлять обработкой исключений в REST API,
 * что упрощает разработку и поддержку кода.
 * -------------------------------------------------------------------------------------------------------------------
 * @ExceptionHandler аннотация используется вместе с аннотацией @RestControllerAdvice в Spring Framework
 * для определения методов, которые будут обрабатывать исключения в REST контроллерах.
 * <p>
 * Когда происходит исключение в REST контроллере, метод, аннотированный с помощью @ExceptionHandler и
 * указывающий на тип исключения, будет вызван для обработки этого исключения. Внутри этого метода можно
 * определить логику обработки исключения и вернуть соответствующий результат.
 * <p>
 * Таким образом, аннотация @ExceptionHandler позволяет определить специальные методы,
 * которые будут вызываться при возникновении определенного типа исключений, и обрабатывать их в
 * соответствии с требованиями приложения. Это упрощает централизованную обработку исключений в REST API.
 * <p>
 * Возвращаемое значение метода, аннотированного с помощью @ExceptionHandler,
 * обычно является объектом класса ResponseEntity<T>, где T - тип данных, который будет возвращен в ответ на запрос.
 * Это позволяет установить статус код, заголовки и тело ответа в соответствии с требованиями приложения.
 * -------------------------------------------------------------------------------------------------------------------
 * MethodArgumentNotValidException - это исключение, которое возникает при некорректных аргументах метода.
 * Оно может быть выброшено, например, при валидации аргументов контроллера в Spring Framework. Это исключение
 * содержит информацию о невалидных аргументах, таких как поля модели, которые не соответствуют
 * заданным правилам валидации.
 * -------------------------------------------------------------------------------------------------------------------
 * UUID (Universally Unique Identifier) в Java представляет собой 128-битное значение,
 * которое используется для идентификации объектов и обеспечения их уникальности.
 * Он может быть использован для генерации уникальных идентификаторов для различных целей,
 * таких как идентификация записей в базе данных, создание временных файлов или идентификация сессий пользователей.
 * -------------------------------------------------------------------------------------------------------------------
 * FieldError - это класс в Spring Framework, который представляет информацию об ошибке, связанной с полем модели.
 * Он содержит информацию о неверном значении поля, сообщение об ошибке и код ошибки.
 * FieldError обычно используется вместе с MethodArgumentNotValidException для предоставления информации
 * о всех невалидных полях модели.
 * --------------------------------------------------------------------------------------------------------------------
 * Этот код является обработчиком исключений для REST-контроллеров в приложении.
 * Он используется для обработки и логирования исключений, которые могут возникнуть при выполнении запросов.
 * Аннотация @RestControllerAdvice указывает,
 * что этот класс является глобальным обработчиком исключений для всех контроллеров в приложении.
 * Метод handleMethodArgumentNotValidException обрабатывает исключение MethodArgumentNotValidException,
 * которое возникает, когда не проходит валидация аргументов метода контроллера.
 * В этом методе создается уникальный идентификатор (id) и собирается описание ошибки из всех полей,
 * которые не прошли валидацию. Затем описание ошибки и текущая дата и время логируются с помощью объекта Logger.
 * Возвращается ответ с HTTP-статусом BAD_REQUEST и объектом OperationStatus,
 * содержащим идентификатор и описание ошибки.
 * Методы handleErrorInputData и handleErrorTransfer аналогичны методу handleMethodArgumentNotValidException,
 * но обрабатывают исключения ErrorInputData и ErrorTransfer соответственно. Они также логируют ошибки и возвращают
 * соответствующие HTTP-статусы и объекты OperationStatus.
 * Объект Logger используется для записи логов в файл "src/main/java/com/example/transfer_money_API/info/logs.txt".
 * Он принимает путь к файлу в качестве параметра в конструкторе.
 */

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private final String LOGGER_PATH = "src/main/java/ru/netology/moneytransferservice/logs/logs.txt";
    private final Logger logger = new Logger(LOGGER_PATH);

    @ExceptionHandler
    public ResponseEntity<OperationStatus> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String id = String.valueOf(UUID.randomUUID());

        StringBuilder description = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            description.append(fieldError.getDefaultMessage()).append(" ");
        }

        logger.log("Date | " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n"
                + "Status | " + description + "\n");

        return new ResponseEntity<>(new OperationStatus(id, description.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<OperationStatus> handleErrorInputData(ErrorInputData e) {
        logger.log("Date | " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n"
                + "Status | " + e.getMessage() + "\n");

        return new ResponseEntity<>(new OperationStatus(
                String.valueOf(UUID.randomUUID()), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<OperationStatus> handleErrorTransfer(ErrorTransfer e) {

        logger.log("Date | " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n"
                + "Status | " + e.getMessage() + "\n");

        return new ResponseEntity<>(new OperationStatus(
                String.valueOf(UUID.randomUUID()), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
