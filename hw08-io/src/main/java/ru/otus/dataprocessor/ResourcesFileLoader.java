package ru.otus.dataprocessor;

import java.io.BufferedInputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        List<Measurement> list;
        try {
            var stream = getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
            list = mapper.readValue(bufferedInputStream, new TypeReference<List<Measurement>>() {});
            bufferedInputStream.close();
        } catch (Exception e) {
            throw new FileProcessException(e);
        }
        //читает файл, парсит и возвращает результат
        return list;
    }
}
