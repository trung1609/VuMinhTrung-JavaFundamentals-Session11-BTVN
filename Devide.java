package Session11.gioi2;

public abstract class Devide {
    private int id;
    private String name;

    public Devide(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public abstract void turnOn();
    public abstract void turnOff();
}
