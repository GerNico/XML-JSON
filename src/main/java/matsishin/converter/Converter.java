package matsishin.converter;

import java.lang.reflect.Field;
import java.util.StringJoiner;

import static matsishin.converter.StrConst.*;

class Converter {
    String convertToXML(Object object) {
        return XML_HEADER + plainObjectToXML(object);
    }

    private static String plainObjectToXML(Object object) {
        Class clazz = object.getClass();

        StringBuilder stringBuilder = new StringBuilder();
        Field[] publicFields = clazz.getDeclaredFields();

        stringBuilder.append(XML_OBJECT_B1).append(clazz.getName()).append(XML_OBJECT_B2);

        if (object instanceof Iterable) {
            Iterable iterable = (Iterable) object;
            for (Object current : iterable) {
                stringBuilder.append(plainObjectToXML(current));
            }
        } else {
            stringBuilder.append(XML_Fields_B);
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    Object current = field.get(object);
                    if (current instanceof Iterable) {
                        stringBuilder.append(plainObjectToXML(current));
                    } else {
                        stringBuilder.append(XML_B1).append(field.getName()).append(XML_B2)
                                .append(current)
                                .append(XML_E1).append(field.getName()).append(XML_E2);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            stringBuilder.append(XML_Fields_E);
        }
        stringBuilder.append(XML_OBJECT_E);
        return stringBuilder.toString();
    }

    String convertToJSON(Object object) {
        Class clazz = object.getClass();

        StringBuilder stringBuilder = new StringBuilder();
        Field[] publicFields = clazz.getDeclaredFields();

        stringBuilder.append(JSON_OBJECT_B1).append(clazz.getName()).append(JSON_OBJECT_B2);
        if (object instanceof Iterable) {
            StringJoiner stringJoiner = new StringJoiner(JSON_DELIMITER);
            Iterable iterable = (Iterable) object;
            stringBuilder.append(JSON_LIST_B);
            for (Object current : iterable) {
                stringJoiner.add(convertToJSON(current));
            }
            stringBuilder.append(stringJoiner);
            stringBuilder.append(JSON_LIST_E);
        } else {
            stringBuilder.append(JSON_FIELDS_B);
            StringJoiner stringJoiner = new StringJoiner(JSON_DELIMITER);
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    Object current = field.get(object);
                    if (current instanceof Iterable) {
                        stringJoiner.add(convertToJSON(current));
                    } else {
                        stringJoiner.add(JSON_NAME_B + field.getName() + JSON_NAME_E + current);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            stringBuilder.append(stringJoiner);
            stringBuilder.append(JSON_LIST_E);
        }
        stringBuilder.append(JSON_E);
        return stringBuilder.toString();
    }
}

