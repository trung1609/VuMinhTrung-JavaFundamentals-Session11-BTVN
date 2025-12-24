package Session11.gioi2;

public class Television extends Devide implements Connectable{
    public Television(int id, String name) {
        super(id, name);
    }

    @Override
    public void connectWifi() {
        System.out.println(getName() + " is connecting Wifi...");
    }

    @Override
    public void turnOn() {
        System.out.println(getName() + " is turning on");
    }

    @Override
    public void turnOff() {
        System.out.println(getName() + " is turning off");
    }
}
