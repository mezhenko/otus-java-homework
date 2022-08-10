package ru.otus.cachehw;

import java.util.Optional;

import ru.otus.cachehw.model.Client;
import ru.otus.cachehw.service.DBServiceClient;

/**
 * @author johnkel
 */
public class ClientService {
    private final HwCache<Long, Client> cache;
    private final DBServiceClient dbServiceClient;

    public ClientService(HwCache<Long, Client> cache, DBServiceClient dbServiceClient) {
        this.cache = cache;
        this.dbServiceClient = dbServiceClient;
    }

    public Client get(long id) {
        Client cacheData = cache.get(id);
        if (cacheData != null) {
            return cacheData;
        }

        Optional<Client> data = dbServiceClient.getClient(id);
        Client result = null;

        if (data.isPresent()) {
            result = data.get();
        }

        cache.put(id, result);
        return result;
    }
}
