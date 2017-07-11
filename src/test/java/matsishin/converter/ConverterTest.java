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
                        "<a>3</a>\n" + "<b>4</b>\n" + "<c>5</c>\n" +
                        "</Fields>" +"</Object>";
        String convertedTriangle = converter.convertToXML(triangle);
        assertTrue("Troubles with triangle", expectedTriangle.equals(convertedTriangle));

        Circle circle = new Circle(5);
        String expectedCircle =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<Fields>\n" +
                        "<radii>5</radii>\n" +
                        "</Fields>" +"</Object>";
        String convertedCircle = converter.convertToXML(circle);
        assertTrue("Troubles with circle", expectedCircle.equals(convertedCircle));

        Square square = new Square(4);
        String expectedSquare =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>4</side>\n" +
                        "</Fields>" +
                        "</Object>";
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
        String expectedXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"java.util.ArrayList\">\n" +
                        "<Object id=\"matsishin.shapes.Triangle\">\n" +
                        "<Fields>\n" +
                        "<a>32</a>\n" + "<b>41</b>\n" + "<c>15</c>\n" +
                        "</Fields>" +"</Object>" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<Fields>\n" +
                        "<radii>10</radii>\n" +
                        "</Fields>" +"</Object>" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>14</side>\n" +
                        "</Fields>" +"</Object>" +
                        "</Object>";
        String convertedXML = converter.convertToXML(shapes);
        assertTrue("Bad format of iterable", expectedXML.equals(convertedXML));
    }

    @Test
    public void XMLConverterAgregation() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        BoxOfShapes boxOfShapes = new BoxOfShapes();
        boxOfShapes.add(triangle);
        boxOfShapes.add(circle);
        boxOfShapes.add(square);
        String expectedXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.BoxOfShapes\">\n" +
                        "<Fields>\n" +
                        "<Object id=\"java.util.ArrayList\">\n" +
                        "<Object id=\"matsishin.shapes.Triangle\">\n" +
                        "<Fields>\n" +
                        "<a>32</a>\n" + "<b>41</b>\n" + "<c>15</c>\n" +
                        "</Fields>" +"</Object>" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<Fields>\n" +
                        "<radii>10</radii>\n" +
                        "</Fields>" +"</Object>" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>14</side>\n" +
                        "</Fields>" +"</Object>" +
                        "</Object>" +
                        "</Fields>" +"</Object>";
        String convertedXML = converter.convertToXML(boxOfShapes);
        assertTrue("Bad format of Box", expectedXML.equals(convertedXML));
    }

    @Test
    public void XMLConverterDeepRecursion() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        BoxOfShapes boxOfShapes = new BoxOfShapes();
        boxOfShapes.add(triangle);
        boxOfShapes.add(circle);
        boxOfShapes.add(square);
        ArrayList<BoxOfShapes> deepArray= new ArrayList<>();
        deepArray.add(boxOfShapes);
        Triangle triangle2 = new Triangle(3, 4, 5);
        Square square2 = new Square(6);
        BoxOfShapes boxOfShapes2 = new BoxOfShapes();
        boxOfShapes2.add(triangle2);
        boxOfShapes2.add(square2);
        deepArray.add(boxOfShapes2);
        String expectedXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"java.util.ArrayList\">\n" +
                        "<Object id=\"matsishin.shapes.BoxOfShapes\">\n" +
                        "<Fields>\n" +
                        "<Object id=\"java.util.ArrayList\">\n" +
                        "<Object id=\"matsishin.shapes.Triangle\">\n" +
                        "<Fields>\n" +
                        "<a>32</a>\n" +
                        "<b>41</b>\n" +
                        "<c>15</c>\n" +
                        "</Fields>" + "</Object>" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<Fields>\n" +
                        "<radii>10</radii>\n" +
                        "</Fields>" + "</Object>" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>14</side>\n" +
                        "</Fields>" +"</Object>" +
                        "</Object>" +
                        "</Fields>" +"</Object>" +
                        "<Object id=\"matsishin.shapes.BoxOfShapes\">\n" +
                        "<Fields>\n" +
                        "<Object id=\"java.util.ArrayList\">\n" +
                        "<Object id=\"matsishin.shapes.Triangle\">\n" +
                        "<Fields>\n" +
                        "<a>3</a>\n" +
                        "<b>4</b>\n" +
                        "<c>5</c>\n" +
                        "</Fields>" + "</Object>" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<Fields>\n" +
                        "<side>6</side>\n" +
                        "</Fields>" + "</Object>" +
                        "</Object>" +
                        "</Fields>" +"</Object>" +
                        "</Object>";
        String convertedXML = converter.convertToXML(deepArray);
        assertTrue("Bad format of Box", expectedXML.equals(convertedXML));
    }

    @Test
    public void JSONConverterPlaneShapes() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(3, 4, 5);
        String expectedTriangle =
                "{\"Object\" : \"matsishin.shapes.Triangle\",\n" +
                        "\"Fields\" : [\"a\" : 3,\n" +
                        "\"b\" : 4,\n" +
                        "\"c\" : 5]}";
        String convertedTriangle = converter.convertToJSON(triangle);
        assertTrue("Troubles with triangle", expectedTriangle.equals(convertedTriangle));

        Circle circle = new Circle(5);
        String expectedCircle =
                "{\"Object\" : \"matsishin.shapes.Circle\",\n" +
                        "\"Fields\" : [\"radii\" : 5]" +
                        "}";
        String convertedCircle = converter.convertToJSON(circle);
        assertTrue("Troubles with circle", expectedCircle.equals(convertedCircle));

        Square square = new Square(4);
        String expectedSquare =
                "{\"Object\" : \"matsishin.shapes.Square\",\n" +
                        "\"Fields\" : [\"side\" : 4]" +
                        "}";
        String convertedSquare = converter.convertToJSON(square);
        assertTrue("Troubles with square", expectedSquare.equals(convertedSquare));
    }

    @Test
    public void JSONConverterComposition() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(triangle);
        shapes.add(circle);
        shapes.add(square);
        String expectedJSON =
                "{\"Object\" : \"java.util.ArrayList\",\n" +
                        "\"consists\" : [\n" +
                        "{\"Object\" : \"matsishin.shapes.Triangle\",\n" +
                        "\"Fields\" : [\"a\" : 32,\n" +
                        "\"b\" : 41,\n" +
                        "\"c\" : 15]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Circle\",\n" +
                        "\"Fields\" : [\"radii\" : 10]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Square\",\n" +
                        "\"Fields\" : [\"side\" : 14]}]}";
        String convertedJSON = converter.convertToJSON(shapes);
        assertTrue("Bad format of iterable", expectedJSON.equals(convertedJSON));
    }

    @Test
    public void JSONConverterAgregation() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        BoxOfShapes boxOfShapes = new BoxOfShapes();
        boxOfShapes.add(triangle);
        boxOfShapes.add(circle);
        boxOfShapes.add(square);
        String expectedJSON =
                "{\"Object\" : \"matsishin.shapes.BoxOfShapes\",\n" +
                        "\"Fields\" : [{\"Object\" : \"java.util.ArrayList\",\n" +
                        "\"consists\" : [\n" +
                        "{\"Object\" : \"matsishin.shapes.Triangle\",\n" +
                        "\"Fields\" : [\"a\" : 32,\n" +
                        "\"b\" : 41,\n" +
                        "\"c\" : 15]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Circle\",\n" +
                        "\"Fields\" : [\"radii\" : 10]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Square\",\n" +
                        "\"Fields\" : [\"side\" : 14]}]}]}";
        String convertedJSON = converter.convertToJSON(boxOfShapes);
        assertTrue("Bad format of Box", expectedJSON.equals(convertedJSON));
    }

    @Test
    public void JSONConverterDeepRecursion() {
        Converter converter = new Converter();
        Triangle triangle = new Triangle(32, 41, 15);
        Circle circle = new Circle(10);
        Square square = new Square(14);
        BoxOfShapes boxOfShapes = new BoxOfShapes();
        boxOfShapes.add(triangle);
        boxOfShapes.add(circle);
        boxOfShapes.add(square);
        ArrayList<BoxOfShapes> deepArray= new ArrayList<>();
        deepArray.add(boxOfShapes);
        Triangle triangle2 = new Triangle(3, 4, 5);
        Square square2 = new Square(6);
        BoxOfShapes boxOfShapes2 = new BoxOfShapes();
        boxOfShapes2.add(triangle2);
        boxOfShapes2.add(square2);
        deepArray.add(boxOfShapes2);
        String expectedJSON =
                "{\"Object\" : \"java.util.ArrayList\",\n" +
                        "\"consists\" : [\n" +
                        "{\"Object\" : \"matsishin.shapes.BoxOfShapes\",\n" +
                        "\"Fields\" : [{\"Object\" : \"java.util.ArrayList\",\n" +
                        "\"consists\" : [\n" +
                        "{\"Object\" : \"matsishin.shapes.Triangle\",\n" +
                        "\"Fields\" : [\"a\" : 32,\n" +
                        "\"b\" : 41,\n" +
                        "\"c\" : 15]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Circle\",\n" +
                        "\"Fields\" : [\"radii\" : 10]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Square\",\n" +
                        "\"Fields\" : [\"side\" : 14]}]}]},\n" +
                        "{\"Object\" : \"matsishin.shapes.BoxOfShapes\",\n" +
                        "\"Fields\" : [{\"Object\" : \"java.util.ArrayList\",\n" +
                        "\"consists\" : [\n" +
                        "{\"Object\" : \"matsishin.shapes.Triangle\",\n" +
                        "\"Fields\" : [\"a\" : 3,\n" +
                        "\"b\" : 4,\n" +
                        "\"c\" : 5]},\n" +
                        "{\"Object\" : \"matsishin.shapes.Square\",\n" +
                        "\"Fields\" : [\"side\" : 6]}]}]}]}";
        String convertedJSON = converter.convertToJSON(deepArray);
        assertTrue("Bad format of Box", expectedJSON.equals(convertedJSON));
    }
}
