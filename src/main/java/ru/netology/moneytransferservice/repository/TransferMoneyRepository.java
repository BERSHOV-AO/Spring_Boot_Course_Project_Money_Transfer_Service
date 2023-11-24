package ru.netology.moneytransferservice.repository;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransferservice.model.ConfirmationData;
import ru.netology.moneytransferservice.model.TransferMoneyData;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Repository аннотация в Spring Framework указывает, что класс является репозиторием, который предоставляет доступ
 * к базе данных или другому источнику данных. Класс, помеченный аннотацией @Repository, обычно используется для
 * выполнения операций чтения и записи данных.
 *
 * Когда класс помечен аннотацией @Repository, Spring автоматически создает экземпляр этого класса и управляет его
 * жизненным циклом. Это позволяет использовать репозиторий в других частях приложения без необходимости создавать
 * экземпляр самостоятельно.
 *
 * CopyOnWriteArrayList используется в ситуациях, когда требуется обеспечить безопасное чтение списка во время
 * параллельной записи. При использовании CopyOnWriteArrayList любые операции записи создают копию списка,
 * что позволяет другим потокам безопасно читать оригинальный список, в то время как запись происходит в копии.
 */

@Getter
@Repository
public class TransferMoneyRepository {

    // Map операции
    private final Map<String, String> operations = new ConcurrentHashMap<>();
    // переводы
    private final Deque<TransferMoneyData> transfers = new ConcurrentLinkedDeque<>();
    // подтверждения
    private final List<ConfirmationData> confirmations = new CopyOnWriteArrayList<>();

//    сохранить данные передачи
    public void saveTransferData(TransferMoneyData transferMoneyData) {
        operations.put(transferMoneyData.getId(), "0000");
        transfers.push(transferMoneyData);
    }

    }

