package Session11.kha2;

public class EWalletPayment extends Payment implements IRefundable {

    public EWalletPayment(double amount) {
        super(amount);
    }

    @Override
    public void refund() {
        System.out.println("Goi ham refund EWalletPayment");
    }

    @Override
    public void pay() {
        System.out.println("Da thanh toan thanh cong qua EWallet voi so tien: " + getAmount());
    }
}
