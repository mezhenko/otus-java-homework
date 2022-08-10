package ru.otus.cachehw.service;

import java.util.List;
import java.util.Optional;

import ru.otus.cachehw.model.Manager;


public interface DBServiceManager {

    Manager saveManager(Manager client);

    Optional<Manager> getManager(long no);

    List<Manager> findAll();
}
