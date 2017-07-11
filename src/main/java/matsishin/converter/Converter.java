package matsishin.converter;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import static matsishin.converter.StrConst.*;

public class Converter {
    public String convertToXML(Object object) {

        StringBuilder stringBuilder = new StringBuilder();
        if (object instanceof Iterable) {
            stringBuilder.append(XML_HEADER1);
            stringBuilder.append("<Iterable>\n");
            List list = (List) object;
            for (Object aList : list) {
                stringBuilder.append(plainObjectToXML(aList));
            }
            stringBuilder.append("</Iterable>");
        } else {
            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            stringBuilder.append(plainObjectToXML(object));
        }
        return stringBuilder.toString();
    }

    public String convertToJSON(Object object) {
        Class clazz = object.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        if (object instanceof Iterable) {
            stringBuilder.append(iterableToJSON((Iterable) object));
        } else {
            stringBuilder.append(plainObjectToJSON(object));
        }
        return stringBuilder.toString();
    }

    private static String plainObjectToXML(Object object) {
        Class clazz = object.getClass();

        StringBuilder stringBuilder = new StringBuilder();
        Field[] publicFields = clazz.getDeclaredFields();

        stringBuilder.append(XML_OBJECT_b1).append(clazz.getName()).append(XML_OBJECT_b2);

        if (object instanceof Iterable) {
            Iterable iterable = (Iterable) object;
            Iterator iterator = iterable.iterator();
            stringBuilder.append(XML_Iterable_b);
            while (iterator.hasNext()) stringBuilder.append(plainObjectToXML(iterator.next()));
            stringBuilder.append(XML_Iterable_e);
        } else {
            for (Field field : publicFields) {
                field.setAccessible(true);
                try {
                    stringBuilder.append(XML_b1).append(field.getName()).append(XML_b2)
                            .append(field.get(object))
                            .append(XML_e1).append(field.getName()).append(XML_e2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        stringBuilder.append(XML_OBJECT_e);
        return stringBuilder.toString();
    }

    private static String plainObjectToJSON(Object object) {
        Class clazz = object.getClass();
        Field[] publicFields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        StringJoiner sj = new StringJoiner(",");
        sj.add("\"class name\" : \"" + clazz.getName() + "\"\n");

        for (Field field : publicFields) {
            field.setAccessible(true);
            try {
                if (field.get(object) instanceof Number || field.get(object) instanceof String) {
                    StringBuilder lsb = new StringBuilder();
                    lsb.append("\" ").append(field.getName()).append(" \" : ")
                            .append(field.get(object))
                            .append("\n");
                    sj.add(lsb);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append(sj);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private static String iterableToJSON(Iterable iterable) {
        Iterator iterator = iterable.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner sj = new StringJoiner(",");
        stringBuilder.append("[");
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof Iterable) {
                sj.add(iterableToJSON((Iterable) object));
            } else {
                sj.add(plainObjectToJSON(object));
            }
        }
        stringBuilder.append(sj.toString());
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

//    private static String iterableToXML(Iterable iterable) {
//        Iterator iterator = iterable.iterator();
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append("<Iterable>\n");
//
//        while (iterator.hasNext()){
//            Object object=iterator.next();
//            try {
//                if (object instanceof Number || object instanceof String) {
//
//                    stringBuilder.append("<").append(field.getName()).append(">")
//                            .append(field.get(object))
//                            .append("</").append(field.getName()).append(">\n");
//                }
//                if (object instanceof Iterable){
//
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        stringBuilder.append("</Iterable>\n");
//        return stringBuilder.toString();
//    }
}

