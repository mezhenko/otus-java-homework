package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorSwitch10And11Fields implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field10(message.getField11()).field11(message.getField10()).build();
    }
}
