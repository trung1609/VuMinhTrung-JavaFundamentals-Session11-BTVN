package Session11.xuatsac2;

import java.util.Scanner;

public class InvoiceDetail {
    private Product product;
    private int quantity;
    private double subTotal;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Product product, int quantity) {
        this.product = product;
        this.subTotal = product.getPrice() * quantity;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void inputData(Scanner sc, Product[] arrProd, int prodIndex) {
        this.product = inputProductId(sc, arrProd, prodIndex);
        this.quantity = inputQuantity(sc);
        this.subTotal = this.product.getPrice() * quantity;
    }

    public Product inputProductId(Scanner sc, Product[] arrProd, int prodIndex) {
        System.out.println("--- Danh Sach San Pham ---");
        if (prodIndex == 0) {
            System.out.println("Chua co san pham nao.");
        } else {

            for (int i = 0; i < prodIndex; i++) {
                System.out.printf("--- San pham thu %d ---", (i + 1));
                arrProd[i].displayData();
            }
            do {
                System.out.print("Nhap ma san pham: ");
                String productId = sc.nextLine();
                for (int i = 0; i < prodIndex; i++) {
                    if (arrProd[i].getProductId().equals(productId)) {
                        return arrProd[i];
                    }
                }
                System.out.println("Ma san pham khong ton tai.");
            } while (true);
        }
        return null;
    }

    public int inputQuantity(Scanner sc) {
        int inputQuantity;
        do {
            System.out.print("Nhap so luong mua: ");
            inputQuantity = Integer.parseInt(sc.nextLine());
            if (inputQuantity < 0) {
                System.err.println("Vui long nhap lai so luong mua.");
            }
        } while (inputQuantity < 0);
        return inputQuantity;
    }

    public void displayData() {
        System.out.println("Ma san pham: " + this.product.getProductId());
        System.out.println("So luong san pham: " + this.quantity);
        System.out.println("Tong tien: " + this.subTotal);
    }

}


