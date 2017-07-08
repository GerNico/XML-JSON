package matsishin.shapes;

public class BoxOfShapes {
    public static void main(String[] args) {
        Shape[] shapes=new Shape[3];
        shapes[0] = new Triangle(3, 4, 5);
        shapes[1] = new Circle(5);
        shapes[2] = new Square(8);
        Converter myConverter=new Converter();
        for (Shape shape:shapes){
            System.out.println(myConverter.convertToXML(shape));
            System.out.println(myConverter.convertToJSON(shape));
        }
    }
}
