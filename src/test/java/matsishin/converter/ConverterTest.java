package matsishin.converter;

import matsishin.shapes.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ConverterTest {

    @Test
    public void XMLConverterPlaneShapes() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(3, 4, 5);
        String expectedTriangle =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Triangle\">\n" +
                        "<Fields>\n" +
                        "<a>3</a>\n" +
                        "<b>4</b>\n" +
                        "<c>5</c>\n" +
                        "</Fields>\n" +
                        "</Object>\n";
        String convertedTriangle = converter.convertToXML(triangle);
        assertTrue("Troubles with triangle", expectedTriangle.equals(convertedTriangle));

        Circle circle = new Circle(5);
        String expectedCircle =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<Fields>\n" +
                        "<radii>5</radii>\n" +
                        "</Fields>\n" +
                        "</Object>\n";
        String convertedCircle = converter.convertToXML(circle);
        assertTrue("Troubles with circle", expectedCircle.equals(convertedCircle));

        Square square = new Square(4);
        String expectedSquare =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>4</side>\n" +
                        "</Fields>\n" +
                        "</Object>\n";
        String convertedSquare = converter.convertToXML(square);
        assertTrue("Troubles with square", expectedSquare.equals(convertedSquare));
    }

    @Test
    public void XMLConverterComposition() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(triangle);
        shapes.add(circle);
        shapes.add(square);
        String expectedXML=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Object id=\"java.util.ArrayList\">\n" +
                "<Object id=\"matsishin.shapes.Triangle\">\n" +
                "<Fields>\n" +
                "<a>32</a>\n" +
                "<b>41</b>\n" +
                "<c>15</c>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "<Object id=\"matsishin.shapes.Circle\">\n" +
                "<Fields>\n" +
                "<radii>10</radii>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "<Object id=\"matsishin.shapes.Square\">\n" +
                "<Fields>\n" +
                "<side>14</side>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "</Object>\n";
        String convertedXML=converter.convertToXML(shapes);
        assertTrue("Bad format of iterable",expectedXML.equals(convertedXML));
    }

    @Test
    public void XMLConverterAgregation(){
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        BoxOfShapes boxOfShapes=new BoxOfShapes();
        boxOfShapes.add(triangle);
        boxOfShapes.add(circle);
        boxOfShapes.add(square);
        String expectedXML=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Object id=\"matsishin.shapes.BoxOfShapes\">\n" +
                "<Fields>\n" +
                "<Object id=\"java.util.ArrayList\">\n" +
                "<Object id=\"matsishin.shapes.Triangle\">\n" +
                "<Fields>\n" +
                "<a>32</a>\n" +
                "<b>41</b>\n" +
                "<c>15</c>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "<Object id=\"matsishin.shapes.Circle\">\n" +
                "<Fields>\n" +
                "<radii>10</radii>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "<Object id=\"matsishin.shapes.Square\">\n" +
                "<Fields>\n" +
                "<side>14</side>\n" +
                "</Fields>\n" +
                "</Object>\n" +
                "</Object>\n" +
                "</Fields>\n" +
                "</Object>\n";
        String convertedXML=converter.convertToXML(boxOfShapes);
        assertTrue("Bad format of Box",expectedXML.equals(convertedXML));
    }
}