package Session11.gioi2;

public class Main {
    static Devide[] devides = new Devide[10];
    static int currentIndex = 0;
    public static void main(String[] args) {
        devides[currentIndex++] = new SmartPhone(1,"Iphone 17");
        devides[currentIndex++] = new Television(2,"Samsung");
        devides[currentIndex++] = new Laptop(3, "Acer Nitro 5");

        for(int i = 0; i < currentIndex; i++){
            devides[i].turnOn();
            devides[i].turnOff();
            if(devides[i] instanceof SmartPhone){
                ((SmartPhone) devides[i]).connectWifi();
            }else if(devides[i] instanceof Laptop){
                ((Laptop) devides[i]).connectWifi();
            }else if(devides[i] instanceof Television){
                ((Television) devides[i]).connectWifi();
            }

            if(devides[i] instanceof SmartPhone){
                ((SmartPhone) devides[i]).charge();
            }else if(devides[i] instanceof Laptop){
                ((Laptop) devides[i]).charge();
            }
        }
    }
}
