package Session11.kha1;

public abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    };
}
