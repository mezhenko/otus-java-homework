package ru.otus.listener.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

public class HistoryListener implements Listener, HistoryReader {
    private final Map<Long, Message> map = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message newMsg = msg.copy();
        map.put(newMsg.getId(), newMsg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        if (map.containsKey(id)) {
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("History{");
        for (Map.Entry<Long, Message> elem : map.entrySet()) {
            sb.append(elem.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
