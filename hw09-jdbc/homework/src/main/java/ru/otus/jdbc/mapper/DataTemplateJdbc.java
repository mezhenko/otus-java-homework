package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.jdbc.mapper.exceptions.NewInstanceConstructorException;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id),
                rs -> {
                    try {
                        if (rs.next()) {
                            return parseResultSet(rs);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });

    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            var tList = new ArrayList<T>();
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tList.add(parseResultSet(rs));
            }
            return tList;
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T instance) {
        List<Object> paramsList = entityClassMetaData.getFieldsWithoutId().stream()
                .map(f -> getFieldValueFromInstance(f, instance))
                .toList();
        try {
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                    paramsList);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T instance) {
        List<Object> paramsList = entityClassMetaData.getFieldsWithoutId().stream()
                .map(f -> getFieldValueFromInstance(f, instance))
                .collect(Collectors.toList());
        try {
            paramsList.add(entityClassMetaData.getIdField().get(instance));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),
                    paramsList);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private T parseResultSet(ResultSet rs) {
        Constructor<T> constructor = entityClassMetaData.getConstructor();
        Object[] params = entityClassMetaData.getAllFields().stream().map(f -> {
                    try {
                        return rs.getObject(f.getName());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();
        try {
            return constructor.newInstance(params);
        } catch (InvocationTargetException|InstantiationException|IllegalAccessException e) {
            throw new NewInstanceConstructorException();
        }
    }

    private Object getFieldValueFromInstance(Field field, T instance) {
        boolean canAccess = field.canAccess(instance);
        field.setAccessible(true);
        Object param;
        try {
            param = field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(canAccess);
        return param;
    }
}
