package matsishin.converter;

import matsishin.shapes.Circle;
import matsishin.shapes.Square;
import matsishin.shapes.Triangle;
import org.junit.Test;

import static org.junit.Assert.*;


public class ConverterTest {

    @Test
    public void XMLConvertionOfPlaneShapes(){
        Converter converter= new Converter();
        Triangle triangle=new Triangle(3,4,5);
        String expectedTriangle=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Object id=\"matsishin.shapes.Triangle\">\n" +
                "<a>3</a>\n" +
                "<b>4</b>\n" +
                "<c>5</c>\n" +
                "</Object>\n";
        String convertedTriangle=converter.convertToXML(triangle);
        assertTrue("Troubles with triangle",expectedTriangle.equals(convertedTriangle));

        Circle circle=new Circle(5);
        String expectedCircle=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Circle\">\n" +
                        "<radii>5</radii>\n" +
                        "</Object>\n";
        String convertedCircle=converter.convertToXML(circle);
        assertTrue("Troubles with circle",expectedCircle.equals(convertedCircle));

        Square square=new Square(4);
        String expectedSquare=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Object id=\"matsishin.shapes.Square\">\n" +
                        "<side>4</side>\n" +
                        "</Object>\n";
        String convertedSquare=converter.convertToXML(square);
        assertTrue("Troubles with square",expectedSquare.equals(convertedSquare));
    }

}