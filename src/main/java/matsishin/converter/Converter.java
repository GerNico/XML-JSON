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

        stringBuilder.append(XML_OBJECT_b1).append(clazz.getName()).append(XML_OBJECT_b2);

        if (object instanceof Iterable) {
            Iterable iterable = (Iterable) object;
            for (Object current : iterable) {
                stringBuilder.append(plainObjectToXML(current));
            }
        } else {
            stringBuilder.append(XML_Fields_b);
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    Object current = field.get(object);
                    if (current instanceof Iterable) {
                        stringBuilder.append(plainObjectToXML(current));
                    } else {
                        stringBuilder.append(XML_b1).append(field.getName()).append(XML_b2)
                                .append(current)
                                .append(XML_e1).append(field.getName()).append(XML_e2);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            stringBuilder.append(XML_Fields_e);
        }
        stringBuilder.append(XML_OBJECT_e);
        return stringBuilder.toString();
    }

    String convertToJSON(Object object) {
        Class clazz = object.getClass();

        StringBuilder stringBuilder = new StringBuilder();
        Field[] publicFields = clazz.getDeclaredFields();

        stringBuilder.append(JSON_OBJECT_b1).append(clazz.getName()).append(JSON_OBJECT_b2);
        if (object instanceof Iterable) {
            StringJoiner stringJoiner = new StringJoiner(JSON_delimiter);
            Iterable iterable = (Iterable) object;
            stringBuilder.append(JSON_list_b);
            for (Object current : iterable) {
                stringJoiner.add(convertToJSON(current));
            }
            stringBuilder.append(stringJoiner);
            stringBuilder.append(JSON_list_e);
        } else {
            stringBuilder.append(JSON_Fields_b);
            StringJoiner stringJoiner = new StringJoiner(JSON_delimiter);
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    Object current = field.get(object);
                    if (current instanceof Iterable) {
                        stringJoiner.add(convertToJSON(current));
                    } else {
                        stringJoiner.add(JSON_name_b + field.getName() + JSON_name_e + current);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            stringBuilder.append(stringJoiner);
            stringBuilder.append(JSON_list_e);
        }
        stringBuilder.append(JSON_e);
        return stringBuilder.toString();
    }
}

