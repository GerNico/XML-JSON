package matsishin.converter;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.StringJoiner;

import static matsishin.converter.StrConst.*;

public class Converter {
    public String convertToXML(Object object) {
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
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    Object current=field.get(object);
                    if (current instanceof Iterable){
                        stringBuilder.append(plainObjectToXML(current));
                    }else {
                        stringBuilder.append(XML_b1).append(field.getName()).append(XML_b2)
                                .append(current)
                                .append(XML_e1).append(field.getName()).append(XML_e2);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        stringBuilder.append(XML_OBJECT_e);
        return stringBuilder.toString();
    }

//    public String convertToJSON(Object object) {
//        Class clazz = object.getClass();
//        StringBuilder stringBuilder = new StringBuilder();
//        if (object instanceof Iterable) {
//            stringBuilder.append(iterableToJSON((Iterable) object));
//        } else {
//            stringBuilder.append(plainObjectToJSON(object));
//        }
//        return stringBuilder.toString();
//    }

//    private static String plainObjectToJSON(Object object) {
//        Class clazz = object.getClass();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        Field[] publicFields = clazz.getDeclaredFields();
//
//        stringBuilder.append(XML_OBJECT_b1).append(clazz.getName()).append(XML_OBJECT_b2);
//
//        if (object instanceof Iterable) {
//            Iterable iterable = (Iterable) object;
//            for (Object current : iterable) {
//                stringBuilder.append(plainObjectToXML(current));
//            }
//        } else {
//            for (Field field : publicFields) {
//                field.setAccessible(true);
//                try {
//                    Object current=field.get(object);
//                    if (current instanceof Iterable){
//                        stringBuilder.append(plainObjectToXML(current));
//                    }else {
//                        stringBuilder.append(XML_b1).append(field.getName()).append(XML_b2)
//                                .append(current)
//                                .append(XML_e1).append(field.getName()).append(XML_e2);
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        stringBuilder.append(XML_OBJECT_e);
//        return stringBuilder.toString();
//    }
}

