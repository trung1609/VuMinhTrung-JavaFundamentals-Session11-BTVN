package Session11.xuatsac2;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private Status status;

    public Product() {
    }

    public Product(String productId, String productName, double price, Status status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void inputData(Scanner sc, Product[] arrProd, int index) {
        this.productId = inputProductId(sc, arrProd, index);
        this.productName = inputProductName(sc, arrProd, index);
        this.price = inputPrice(sc);
        inputStatus(sc);
    }

    public String inputProductId(Scanner sc, Product[] arrProd, int index) {
        String regex = "[C|S|A]\\w{3}";
        do {
            System.out.print("Nhap ma san pham: ");
            String product_id = sc.nextLine();
            if (Pattern.matches(regex, product_id)) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProd[i].getProductId().equals(product_id)) {
                        System.err.println("Ma san pham da ton  tai");
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return product_id;
                }
            }
            System.err.println("Vui long nhap lai ma san pham.");
        } while (true);
    }

    public String inputProductName(Scanner sc, Product[] arrProd, int index) {
        do {
            System.out.print("Nhap ten san pham: ");
            String productName = sc.nextLine();
            if (productName.length() < 10 || productName.length() > 50) {
                System.err.println("Vui long nhap lai ten san pham.");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrProd[i].getProductName().equalsIgnoreCase(productName)) {
                    System.err.println("Ten san pham da ton  tai");
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                return productName;
            }
        } while (true);
    }

    public double inputPrice(Scanner sc) {
        double product_price;
        do {
            System.out.print("Nhap gia san pham: ");
            product_price = Double.parseDouble(sc.nextLine());
            if (product_price <= 0) {
                System.err.println("Vui long nhap lai gia san pham.");
            }
        } while (product_price <= 0);
        return product_price;
    }

    public boolean checkStatus(String status) {
        return status.equals("AVAILABLE")
                || status.equals("OUT_OF_STOCK")
                || status.equals("STOP_SELLING");
    }

    public void inputStatus(Scanner sc) {
        String product_status;
        do {
            System.out.print("Nhap trang thai san pham: ");
            product_status = sc.nextLine().toUpperCase();
            if (!checkStatus(product_status)) {
                System.err.println("Vui long nhap lai trang thai san pham.");
                continue;
            }
        } while (!checkStatus(product_status));
        this.status = Status.valueOf(product_status);
    }

    public void displayData() {
        System.out.println("Ma san pham: " + productId);
        System.out.println("Ten san pham: " + productName);
        System.out.println("Gia san pham: " + price);
        System.out.println("Trang thai san pham: " + status);
        System.out.println("");
    }
}
