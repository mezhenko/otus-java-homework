package ru.otus.dataprocessor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.otus.model.Measurement;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        return data.stream()
                .reduce(new TreeMap<>(),
                        (acum, meas) -> {
                            String key = meas.getName();
                            Double value = acum.containsKey(key) ? acum.get(key) + meas.getValue() : meas.getValue();
                            acum.put(key, value);
                            return acum;
                        },
                        (acum1, acum2) -> {
                            acum1.forEach((key, value1) -> {
                                Double value = acum2.containsKey(key) ? acum2.get(key) + value1 : value1;
                                acum2.put(key, value);
                            });
                            return acum2;
                        });
    }
}
