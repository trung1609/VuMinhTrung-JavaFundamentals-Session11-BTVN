package Session11.kha2;

public abstract class Payment {
    private double amount;
    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void pay();
    public void printAmount() {

    }

    public double getAmount() {
        return amount;
    }
}
