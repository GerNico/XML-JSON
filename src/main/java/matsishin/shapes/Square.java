package matsishin.shapes;

public class Square implements Shape {

    private Long side;

    public boolean isValid() {
        return side>0;
    }

    Square(long side) {
        this.side = side;
    }

}
