package ru.otus.processor.homework;


import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProcessorWithEvenSecondExceptionTest {

    private Message message;

    @Test
    @DisplayName("Тестируем процессор, кидающий исключения в четные секунды, на сообщении без datetime")
    void handleMessageWithoutDatetime() {
        Processor processor = new ProcessorWithEvenSecond(LocalDateTime::now);
        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @Test
    @DisplayName("Тестируем процессор, кидающий исключения в четные секунды, на сообщении с нечетной секундной")
    void handleMessageWithOddSeconds() {
        Processor processor = new ProcessorWithEvenSecond(() ->
                LocalDateTime.of(1970, 1,1,1,1, 21));

        assertDoesNotThrow(() -> {
            processor.process(message);
        });

    }

    @Test
    @DisplayName("Тестируем процессор, кидающий исключения в четные секунды, на сообщении с четной секундной")
    void handleMessageWithEvenSeconds() {
        Processor processor = new ProcessorWithEvenSecond(() ->
                LocalDateTime.of(1970, 1,1,1,1, 10));
        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @BeforeEach
    void beforeEach() {
        message = new Message.Builder(1L).build();
    }
}