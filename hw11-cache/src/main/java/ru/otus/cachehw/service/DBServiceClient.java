package ru.otus.cachehw.service;

import java.util.List;
import java.util.Optional;

import ru.otus.cachehw.model.Client;

public interface DBServiceClient {

    Client saveClient(Client client);

    Optional<Client> getClient(long id);

    List<Client> findAll();
}
