package matsishin.shapes;

import java.lang.reflect.Field;

class Converter {
    String convertToXML(Object object) {
        Class clazz=object.getClass();
        Field[] publicFields = clazz.getDeclaredFields();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<object id=\"").append(clazz.getName()).append(">\n");
        for (Field field : publicFields) {
            field.setAccessible(true);
            try {
                stringBuilder.append("<").append(field.getName()).append(">")
                        .append(field.get(object))
                        .append("</").append(field.getName()).append(">\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("</object>\n");
        return stringBuilder.toString();
    }
    String convertToJSON(Object object) {
        Class clazz=object.getClass();
        Field[] publicFields = clazz.getDeclaredFields();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append("\"class name\" : \"").append(clazz.getName()).append("\",\n");
        for (Field field : publicFields) {
            field.setAccessible(true);
            try {
                stringBuilder.append("\" ").append(field.getName()).append(" \" : ")
                        .append(field.get(object))
                        .append(",\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-2);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}
