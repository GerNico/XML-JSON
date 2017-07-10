package matsishin.shapes;

public class Circle implements Shape {
    public boolean isValid() {
        return radii>0;
    }
    private Long radii;

    public Circle(long radii) {
        this.radii = radii;
    }

}
