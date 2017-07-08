package matsishin.shapes;

public class Triangle implements Shape{
    public boolean isValid() {
        return a+b>c && b+c>a && c+a>b;
    }
    private Long a;
    private Long b;
    private Long c;

    Triangle(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

}
