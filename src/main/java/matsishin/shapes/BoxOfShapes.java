package matsishin.shapes;

import java.util.ArrayList;
import java.util.List;

public class BoxOfShapes {
    List<Shape> shapes = null;

    public BoxOfShapes() {
        shapes=new ArrayList<>();
    }

    public BoxOfShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void add(Shape shape) {
        if (shapes != null) shapes.add(shape);
        else {
            shapes = new ArrayList<>();
            shapes.add(shape);
        }

    }
}
