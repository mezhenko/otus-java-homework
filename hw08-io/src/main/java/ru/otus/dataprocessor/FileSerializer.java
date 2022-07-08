package ru.otus.dataprocessor;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileSerializer implements Serializer {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        try {
            var file = new File(fileName);
            mapper.writeValue(file, data);
        } catch (Exception e) {
            throw new FileProcessException(e);
        }
    }
}
