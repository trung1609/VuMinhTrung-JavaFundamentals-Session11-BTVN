package Session11.kha1;

public class Main {
    static Shape[] shapes = new Shape[10];
    static int currentIndex = 0;
    public static void main(String[] args) {
        shapes[currentIndex++] = new Circle("Circle", 10);
        shapes[currentIndex++] = new Rectangle("Rectangle", 5, 10);
        for (int i = 0; i < currentIndex; i++) {
            shapes[i].displayInfo();
            if(shapes[i] instanceof Circle) {
                ((Circle) shapes[i]).draw();
            }else if(shapes[i] instanceof Rectangle) {
                ((Rectangle) shapes[i]).draw();
            }
        }
    }


}
