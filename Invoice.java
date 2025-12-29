package Session11.xuatsac2;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Invoice {
    private String invoiceId;
    private String customerName;
    private Date invoiceDate;
    private InvoiceDetail[] invoiceDetails;
    private double totalAmount;

    public Invoice() {
    }

    public Invoice(String invoiceId, String customerName, InvoiceDetail[] invoiceDetails, double totalAmount) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.invoiceDate = Calendar.getInstance().getTime();
        this.invoiceDetails = invoiceDetails;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public InvoiceDetail[] getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetail[] invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void inputData(Scanner sc, Invoice[] arrInvoice, int invoiceIndex, InvoiceDetail[] details, int detailIndex) {
        this.invoiceId = inputInvoiceId(sc, arrInvoice, invoiceIndex);
        this.customerName = inputCustomerName(sc);
        this.invoiceDetails = details;
        calculateTotalAmount(details, detailIndex);
    }

    public String inputInvoiceId(Scanner sc, Invoice[] arrInvoice, int invoiceIndex) {
        String regex = "HD\\d{4}";
        do {
            System.out.print("Nhap ma hoa don: ");
            String invoiceId = sc.nextLine();
            if (Pattern.matches(regex, invoiceId)) {
                boolean isExists = false;
                for (int i = 0; i < invoiceIndex; i++) {
                    if (arrInvoice[i].getInvoiceId().equals(invoiceId)) {
                        System.err.println("Ma hoa don da ton tai.");
                        isExists = true;
                        break;
                    }
                }
                if (!isExists) {
                    return invoiceId;
                }
            }
        } while (true);
    }

    public String inputCustomerName(Scanner sc) {
        String customerNameInput = null;
        do {
            System.out.print("Nhap ten khach hang: ");
            if (sc.hasNextLine()) {
                System.err.println("Vui long nhap lai ten khach hang.");
                continue;
            }
            customerNameInput = sc.nextLine();
        } while (!sc.hasNextLine());
        return customerNameInput;
    }

    public void calculateTotalAmount(InvoiceDetail[] details, int detailsIndex) {
        double total = 0;
        for (int i = 0; i < detailsIndex; i++) {
            total += details[i].getSubTotal();
        }
        this.totalAmount = total;
    }

    public void displayData() {
        System.out.println("Ma hoa don: " + invoiceId);
        System.out.println("Ten khach hang: " + customerName);
        System.out.println("Ngay lap hoa don: " + invoiceDate);
        System.out.println("Hoa don chi tiet:");
        for (int i = 0; i < invoiceDetails.length; i++) {
            System.out.println("- " + invoiceDetails[i].getProduct().getProductName() + " | " + invoiceDetails[i].getQuantity() + " | " + invoiceDetails[i].getSubTotal());
        }
        System.out.println("Thanh tien: " + totalAmount);
        System.out.println();
    }
}
