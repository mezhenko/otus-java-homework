package ru.otus.cachehw.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

/**
 * @author johnkel
 */
public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{

    private final EntityClassMetaData<T> entityClass;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClass) {
        this.entityClass = entityClass;
    }
    @Override
    public String getSelectAllSql() {
        StringBuilder sb = new StringBuilder("SELECT ");

        for (Field f : entityClass.getAllFields()) {
            sb.append(String.format("%s, ", f.getName()));
        }
        sb.append(String.format("WHERE %s = ?", entityClass.getIdField().getName()));
        return sb.toString();
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("SELECT * FROM %s WHERE %s = ?;", entityClass.getName(), entityClass.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        return String.format("INSERT INTO %s(%s) VALUES (%s);",
                entityClass.getName(),
                entityClass.getFieldsWithoutId().stream().map(Field::getName).collect(Collectors.joining(",")),
                entityClass.getFieldsWithoutId().stream().map(f -> "?").collect(Collectors.joining(","))
            );
    }

    @Override
    public String getUpdateSql() {
        StringBuilder sb = new StringBuilder("UPDATE %s SET ");

        for (Field f : entityClass.getFieldsWithoutId()) {
            sb.append(String.format("%s = ?, ", f.getName()));
        }
        sb.append(String.format("WHERE %s = ?", entityClass.getIdField().getName()));
        String sql = sb.toString();
        return sql;
    }
}
