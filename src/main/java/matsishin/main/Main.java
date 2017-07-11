package matsishin.main;

import matsishin.converter.Converter;
import matsishin.shapes.BoxOfShapes;
import matsishin.shapes.Circle;
import matsishin.shapes.Square;
import matsishin.shapes.Triangle;

public class Main {
    public static void main(String[] args) {
        Converter myConverter=new Converter();
        BoxOfShapes boxOfShapes=new BoxOfShapes();
        boxOfShapes.add(new Triangle(3,4,5));
        boxOfShapes.add(new Triangle(4,5,6));
        boxOfShapes.add(new Circle(4));
        boxOfShapes.add(new Square(8));

        Triangle triangle=new Triangle(4,5,6);
        System.out.println(myConverter.convertToXML(triangle));
//        System.out.println(myConverter.convertToJSON(triangle));

//        System.out.println(myConverter.convertToXML(boxOfShapes));
        System.out.println();
//        System.out.println(myConverter.convertToJSON(boxOfShapes));
    }
}