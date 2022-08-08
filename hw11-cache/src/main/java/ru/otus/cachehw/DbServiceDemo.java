package ru.otus.cachehw;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.datasource.DriverManagerDataSource;
import ru.otus.cachehw.mapper.repository.executor.DbExecutorImpl;
import ru.otus.cachehw.model.Client;
import ru.otus.cachehw.repository.ClientDataTemplateJdbc;
import ru.otus.cachehw.service.DbServiceClientImpl;
import ru.otus.cachehw.sessionmanager.TransactionRunnerJdbc;

public class DbServiceDemo {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();
///
        var clientTemplate = new ClientDataTemplateJdbc(dbExecutor); //реализация DataTemplate, заточена на Client

///
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, clientTemplate);
        for (var i = 1; i < 11; i++) {
            dbServiceClient.saveClient(new Client(String.format("client number — %s", i)));
        }

        HwCache<Long, Client> clientCache = new MyCache();
        ClientService clientManager = new ClientService(clientCache, dbServiceClient);

        log.info("cache warming start");
        for (var i = 1; i < 11; i++) {
            clientManager.get(i);
        }
        log.info("cache warming end");

        log.info("with cache start");
        for (var i = 1; i < 11; i++) {
            clientManager.get(i);
        }
        log.info("with cache end");
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
