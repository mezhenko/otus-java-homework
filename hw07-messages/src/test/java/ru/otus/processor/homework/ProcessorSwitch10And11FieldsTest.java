package ru.otus.processor.homework;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessorSwitch10And11FieldsTest {

    @Test
    @DisplayName("Тестируем процессор, меняющий поля местами")
    void handleProcessorsTest() {
        //given
        var message = new Message.Builder(1L).field10("field10").field11("field11").build();

        var processor = new ProcessorSwitch10And11Fields();


        var result = processor.process(message);

        //then
        assertEquals("field11", result.getField10());
        assertEquals("field10", result.getField11());
    }
}