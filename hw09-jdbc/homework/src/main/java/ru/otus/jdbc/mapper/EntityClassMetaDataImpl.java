package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import ru.otus.crm.annotations.Column;
import ru.otus.crm.annotations.Id;
import ru.otus.jdbc.mapper.exceptions.ThereIsNoConstructorException;
import ru.otus.jdbc.mapper.exceptions.ThereIsNoIdException;
import ru.otus.jdbc.mapper.exceptions.TooMuchConstructorsException;
import ru.otus.jdbc.mapper.exceptions.TooMuchIdsException;

/**
 * @author johnkel
 */
public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T>{

    private final Field id;
    private final List<Field> nonIdFields;
    private final List<Field> allFields;
    private final Constructor<T> constructor;
    private final String name;

    private EntityClassMetaDataImpl(String name, Constructor<T> constructor,
                                    Field id, List<Field> nonIdFields, List<Field> allFields) {
        this.name = name;
        this.id = id;
        this.nonIdFields = nonIdFields;
        this.allFields = allFields;
        this.constructor = constructor;
    }

    public static <E> EntityClassMetaDataImpl<E> createByClass(Class<E> clazz){
        Constructor<E>[] constructorsArray = (Constructor<E>[]) clazz.getDeclaredConstructors();
        List<Constructor<E>> constructors = Arrays.stream(constructorsArray)
                .filter(c -> c.isAnnotationPresent(ru.otus.crm.annotations.Constructor.class))
                .toList();
        if (constructors.isEmpty()) {
            throw new ThereIsNoConstructorException();
        }
        if (constructors.size() > 1) {
            throw new TooMuchConstructorsException();
        }
        Constructor<E> constructor = constructors.get(0);

        List<Field> idsField = getStreamFields(clazz)
                .filter(f -> f.isAnnotationPresent(Id.class))
                .toList();
        if (idsField.isEmpty()) {
            throw new ThereIsNoIdException();
        }
        if (idsField.size() > 1) {
            throw new TooMuchIdsException();
        }

        Field id = idsField.get(0);
        List<Field> allFields = getStreamFields(clazz)
                .filter(f -> f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(Id.class))
                .toList();
        List<Field> nonIdFields = getStreamFields(clazz)
                .filter(f -> f.isAnnotationPresent(Column.class))
                .toList();
        String name = clazz.getSimpleName().toLowerCase(Locale.ROOT);

        return new EntityClassMetaDataImpl<>(name, constructor, id, nonIdFields, allFields);
    }

    private static Stream<Field> getStreamFields(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return id;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return nonIdFields;
    }
}
