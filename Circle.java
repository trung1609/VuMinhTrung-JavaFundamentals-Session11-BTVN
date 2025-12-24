package Session11.kha1;

public class Circle extends Shape implements Drawable {
    private double radius;
    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
        System.out.println("--------------------------");
    }

    @Override
    public double getArea() {
        return 3.14 * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * 3.14 * radius;
    }
}
