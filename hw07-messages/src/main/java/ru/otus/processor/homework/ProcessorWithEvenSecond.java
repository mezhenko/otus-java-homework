package ru.otus.processor.homework;

import ru.otus.model.DateTimeProvider;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorWithEvenSecond implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorWithEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if ((dateTimeProvider.getDate().getSecond() % 2) == 0) {
            throw new EvenSecondException();
        }
        return message;
    }
}
