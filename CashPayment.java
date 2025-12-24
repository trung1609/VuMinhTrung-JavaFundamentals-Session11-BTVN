package Session11.kha2;

public class CashPayment extends Payment{
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Thanh toan khi nhan hang voi so tien: "+ getAmount());
    }
}
