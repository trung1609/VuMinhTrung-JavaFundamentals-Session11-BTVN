package Session11.kha2;

public class CreditCardPayment extends Payment implements IRefundable{

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void refund() {
        System.out.println("Goi ham refund CreditCardPayment");
    }

    @Override
    public void pay() {
        System.out.println("Da thanh toan thanh cong qua CreditCart voi so tien: "+getAmount());
    }
}
