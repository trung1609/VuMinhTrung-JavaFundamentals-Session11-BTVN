package Session11.kha2;

public class Main {
    static Payment[] payments = new Payment[10];
    static int currentIndex = 0;
    public static void main(String[] args) {
        payments[currentIndex++] = new CashPayment(10000);
        payments[currentIndex++] = new CreditCardPayment(20000);
        payments[currentIndex++] = new EWalletPayment(30000);
        for (int i = 0; i < currentIndex; i++) {
            payments[i].pay();
            if(payments[i] instanceof CreditCardPayment){
                ((CreditCardPayment) payments[i]).refund();
            }else if(payments[i] instanceof EWalletPayment){
                ((EWalletPayment) payments[i]).refund();
            }
        }
    }
}
