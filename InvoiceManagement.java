package Session11.xuatsac2;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

public class InvoiceManagement {
    static Product[] arrProduct = new Product[100];
    static int currentProductIndex = 0;
    static Invoice[] arrInvoice = new Invoice[100];
    static int currentInvoiceIndex = 0;
    static int currentIdInvoice = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        addData(arrProduct);
        do {
            System.out.println("================= QUAN LY HOA DON =================");
            System.out.println("1. Quan ly san pham");
            System.out.println("2. Quan ly hoa don");
            System.out.println("3. Bao cao doanh thu");
            System.out.println("4. Thoat");
            System.out.println("===================================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    productManagement(sc);
                    break;
                case 2:
                    invoiceManagement(sc);
                    break;
                case 3:
                    reportTotalAmount(sc);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }

    public static void addData(Product[] products) {
        products[0] = new Product("A101", "Ao Polo Nam", 200000, Status.AVAILABLE);
        products[1] = new Product("A102", "Ao Polo Nu", 200200, Status.OUT_OF_STOCK);
        products[2] = new Product("A103", "Ao Phong Trang", 560000, Status.AVAILABLE);
        products[3] = new Product("S104", "Quan Bo Rong", 100000, Status.AVAILABLE);
        products[4] = new Product("S105", "Ao Tay Nam", 5400000, Status.STOP_SELLING);
        products[5] = new Product("C106", "Vay Da Nu", 5600000, Status.OUT_OF_STOCK);
        products[6] = new Product("A107", "Quan Tat Nu", 910000, Status.AVAILABLE);
        products[7] = new Product("C108", "Ao Phao Unisex", 130000, Status.AVAILABLE);
        currentProductIndex += 7;
    }

    public static void productManagement(Scanner sc) {
        do {
            System.out.println("================= QUAN LY SAN PHAM ================");
            System.out.println("1. Them san pham");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Cap nhat thong tin san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Tim kiem san pham theo ten");
            System.out.println("6. Thoat");
            System.out.println("====================================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    updateProduct(sc);
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    searchProduct(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }


    public static void addProduct(Scanner sc) {
        System.out.print("Nhap so san pham muon them: ");
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.printf("Nhap thong tin san pham thu %d: \n", i + 1);
            arrProduct[currentProductIndex] = new Product();
            arrProduct[currentProductIndex].inputData(sc, arrProduct, currentProductIndex);
            currentProductIndex++;
        }
    }

    public static void displayProduct() {
        for (int i = 0; i < currentProductIndex; i++) {
            System.out.printf("--- San Pham Thu %d ---\n", i + 1);
            arrProduct[i].displayData();
        }
    }

    public static int checkProductId(String product_id) {
        for (int i = 0; i < currentProductIndex; i++) {
            if (arrProduct[i].getProductId().equals(product_id)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateProduct(Scanner sc) {
        if (currentProductIndex == 0) {
            System.out.println("Chua co san pham nao duoc them.");
        } else {
            System.out.print("Nhap ma san pham muon cap nhat: ");
            String product_id = sc.nextLine();
            int indexUpdate = checkProductId(product_id);
            if (indexUpdate == -1) {
                System.err.println("Ma san pham khong ton tai.");
            } else {
                do {
                    System.out.println("1. Cap nhat ten san pham");
                    System.out.println("2. Cap nhat gia san pham");
                    System.out.println("3. Cap nhat trang thai san pham");
                    System.out.println("4. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            String newProductName;
                            do {
                                System.out.print("Nhap ten san pham moi: ");
                                newProductName = sc.nextLine();
                                if (newProductName.length() < 10 || newProductName.length() > 50) {
                                    System.err.println("Vui long nhap lai ten san pham.");
                                    continue;
                                }
                                boolean isExist = false;
                                for (int i = 0; i < currentProductIndex; i++) {
                                    if (arrProduct[i].getProductName().equalsIgnoreCase(newProductName)) {
                                        System.err.println("Ten san pham da ton  tai");
                                        isExist = true;
                                        break;
                                    }
                                }
                                if (!isExist) {
                                    arrProduct[indexUpdate].setProductName(newProductName);
                                }
                            } while (newProductName.length() < 10 || newProductName.length() > 50);
                            System.out.println("Cap nhat ten moi thanh cong.");
                            break;
                        case 2:
                            double newProductPrice;
                            do {
                                System.out.print("Nhap gia san pham moi: ");
                                newProductPrice = Double.parseDouble(sc.nextLine());
                                if (newProductPrice <= 0) {
                                    System.err.println("Vui long nhap lai gia san pham.");
                                }
                            } while (newProductPrice <= 0);
                            arrProduct[indexUpdate].setPrice(newProductPrice);
                            System.out.println("Cap nhat gia moi thanh cong.");
                            break;
                        case 3:
                            String newProductStatus;
                            do {
                                System.out.print("Nhap trang thai moi cua san pham: ");
                                newProductStatus = sc.nextLine().toUpperCase();
                                if (!newProductStatus.equals("AVAILABLE") && !newProductStatus.equals("OUT_OF_STOCK") && !newProductStatus.equals("STOP_SELLING")) {
                                    System.err.println("Vui long nhap lai trang thai san pham.");
                                }
                            } while (!newProductStatus.equals("AVAILABLE") && !newProductStatus.equals("OUT_OF_STOCK") && !newProductStatus.equals("STOP_SELLING"));
                            arrProduct[indexUpdate].setStatus(Status.valueOf(newProductStatus));
                            System.out.println("Cap nhat trang thai moi thanh cong.");
                            break;
                        case 4:
                            return;
                        default:
                            System.err.println("Vui long nhap lua chon phu hop.");
                    }
                } while (true);
            }
        }
    }

    public static void deleteProduct(Scanner sc) {
        if (currentProductIndex == 0) {
            System.out.println("Chua co san pham nao duoc them.");
        } else {
            System.out.print("Nhap ma san pham can xoa: ");
            String product_id = sc.nextLine();
            int indexDelete = checkProductId(product_id);
            if (indexDelete == -1) {
                System.err.println("Ma san pham khong ton tai.");
            } else {
                for (int i = 0; i < currentInvoiceIndex; i++) {
                    InvoiceDetail[] detail = arrInvoice[i].getInvoiceDetails();
                    for (InvoiceDetail invoiceDetail : detail) {
                        if (invoiceDetail.getProduct().getProductId().equals(product_id)) {
                            System.err.println("San pham da co trong hoa don. Khong the xoa san pham");
                            return;
                        }
                    }
                }
                for (int i = indexDelete; i < currentProductIndex - 1; i++) {
                    arrProduct[i] = arrProduct[i + 1];
                }
                currentProductIndex--;
                System.out.println("Xoa san pham thanh cong.");
            }
        }
    }

    public static void searchProduct(Scanner sc) {
        if (currentProductIndex == 0) {
            System.out.println("Chua co san pham nao duoc them.");
        } else {
            System.out.print("Nhap ten san pham can tim: ");
            String product_name = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentProductIndex; i++) {
                if (arrProduct[i].getProductName().toLowerCase().contains(product_name.toLowerCase())) {
                    arrProduct[i].displayData();
                    isFound = true;
                }
            }
            if (!isFound) {
                System.err.println("Khong tim thay san pham.");
            }

        }
    }

    public static void invoiceManagement(Scanner sc) {
        do {
            System.out.println("================= QUAN LY HOA DON ================");
            System.out.println("1. Them hoa don");
            System.out.println("2. Hien thi danh sach hoa don");
            System.out.println("3. Cap nhat thong tin hoa don");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Tim hoa don theo ma");
            System.out.println("6. Tim hoa don theo ten khach hang");
            System.out.println("7. Thoat");
            System.out.println("==================================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addInvoice(sc);
                    break;
                case 2:
                    displayInvoice();
                    break;
                case 3:
                    updateInvoice(sc);
                    break;
                case 4:
                    deleteInvoice(sc);
                    break;
                case 5:
                    searchInvoice(sc);
                    break;
                case 6:
                    searchInvoiceByCustomerName(sc);
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }

    public static String getInvoiceId() {
        String root = "000";
        String result = root + currentIdInvoice;
        if (result.length() > 4) {
            result = result.substring(result.length() - 4);
        }
        currentIdInvoice++;
        return "HD" + result;
    }

    public static void addInvoice(Scanner sc) {
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceId(getInvoiceId());
        System.out.print("Nhap ten khach hang: ");
        newInvoice.setCustomerName(sc.nextLine());
        newInvoice.setInvoiceDate(Calendar.getInstance().getTime());
        InvoiceDetail[] arrInvoiceDetail = new InvoiceDetail[50];
        int currentIndex = 0;
        String choiceId;
        do {
            displayProduct();
            System.out.print("Nhap vao id cua san pham muon them hoac nhap 0 de dung chon: ");
            choiceId = sc.nextLine();
            if (choiceId.equals("0")) {
                break;
            }
            int indexAdd = checkProductId(choiceId);
            if (indexAdd == -1) {
                System.err.println("Ma san pham khong ton tai");
            } else {
                System.out.print("Nhap so luong san pham: ");
                int quantity = Integer.parseInt(sc.nextLine());
                int isExist = -1;
                for (int i = 0; i < currentIndex; i++) {
                    if (arrInvoiceDetail[i].getProduct().getProductId().equals(choiceId)) {
                        isExist = i;
                        break;
                    }
                }
                if (isExist != -1) {
                    arrInvoiceDetail[isExist].setQuantity(arrInvoiceDetail[isExist].getQuantity() + quantity);
                    arrInvoiceDetail[isExist].setSubTotal(quantity * arrProduct[indexAdd].getPrice());
                } else {
                    InvoiceDetail newInvoiceDetail = new InvoiceDetail();
                    newInvoiceDetail.setQuantity(quantity);
                    newInvoiceDetail.setProduct(arrProduct[indexAdd]);
                    newInvoiceDetail.setSubTotal(quantity * arrProduct[indexAdd].getPrice());
                    arrInvoiceDetail[currentIndex] = newInvoiceDetail;
                    currentIndex++;
                }
            }
        } while (true);
        double totalAmount = 0;
        for (int i = 0; i < currentIndex; i++) {
            totalAmount += arrInvoiceDetail[i].getSubTotal();
        }
        InvoiceDetail[] newInvoiceDetail = new InvoiceDetail[currentIndex];
        for (int i = 0; i < currentIndex; i++) {
            newInvoiceDetail[i] = arrInvoiceDetail[i];
        }
        newInvoice.setTotalAmount(totalAmount);
        newInvoice.setInvoiceDetails(newInvoiceDetail);
        arrInvoice[currentInvoiceIndex] = newInvoice;
        currentInvoiceIndex++;
        System.out.println("Them hoa don thanh cong.");
    }


    public static void displayInvoice() {
        if (currentInvoiceIndex == 0) {
            System.out.println("Chua co hoa don nao duoc them.");
        } else {
            for (int i = 0; i < currentInvoiceIndex; i++) {
                arrInvoice[i].displayData();
            }
        }
    }

    public static int checkInvoiceId(String invoid_id) {
        for (int i = 0; i < currentInvoiceIndex; i++) {
            if (arrInvoice[i].getInvoiceId().equals(invoid_id)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateInvoice(Scanner sc) {
        if (currentInvoiceIndex == 0) {
            System.out.println("Chua co hoa don nao duoc them.");
        } else {
            System.out.print("Nhap ma hoa don can cap nhat: ");
            String invoice_id = sc.nextLine();
            int indexUpdate = checkInvoiceId(invoice_id);
            if (indexUpdate == -1) {
                System.err.println("Ma hoa don khong ton tai.");
            } else {
                do {
                    System.out.println("1. Cap nhat ten khach hang");
                    System.out.println("2. Cap nhat hoa don chi tiet");
                    System.out.println("3. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.print("Nhap ten khach hang moi: ");
                            arrInvoice[indexUpdate].setCustomerName(sc.nextLine());
                            System.out.println("Cap nhat ten khach hang thanh cong");
                            break;
                        case 2:

                            break;
                        case 3:
                            return;
                        default:
                            System.err.println("Vui long nhap lua chon phu hop.");
                    }
                } while (true);
            }
        }
    }

    public static void deleteInvoice(Scanner sc) {
        if (currentInvoiceIndex == 0) {
            System.out.println("Chua co hoa don nao duoc them");
        } else {
            System.out.print("Nhap ma hoa don can xoa: ");
            String invoice_id = sc.nextLine();
            int indexDelete = checkInvoiceId(invoice_id);
            if (indexDelete == -1) {
                System.err.println("Ma hoa don khong ton tai.");
            } else {
                for (int i = indexDelete; i < currentInvoiceIndex - 1; i++) {
                    arrInvoice[i] = arrInvoice[i + 1];
                }
                currentInvoiceIndex--;
                System.out.println("Xoa hoa don thanh cong");
            }
        }
    }

    public static void searchInvoice(Scanner sc) {
        if (currentInvoiceIndex == 0) {
            System.out.println("Chua co hoa don nao duoc them");
        } else {
            System.out.print("Nhap ma hoa don can tim: ");
            String invoice_id = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentInvoiceIndex; i++) {
                if (arrInvoice[i].getInvoiceId().equals(invoice_id)) {
                    arrInvoice[i].displayData();
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay hoa don");
            }
        }
    }

    public static void searchInvoiceByCustomerName(Scanner sc) {
        if (currentInvoiceIndex == 0) {
            System.out.println("Chua co hoa don nao duoc them");
        } else {
            System.out.print("Nhap ten khach hang can tim: ");
            String customer_name = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentInvoiceIndex; i++) {
                if (arrInvoice[i].getCustomerName().toLowerCase().contains(customer_name.toLowerCase())) {
                    arrInvoice[i].displayData();
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay hoa don");
            }
        }
    }

    public static void reportTotalAmount(Scanner sc) {
        do {
            System.out.println("================= QUAN LY DOANH THU =================");
            System.out.println("1. Tinh tong doanh thu tat ca hoa don");
            System.out.println("2. Tim hoa don co gia tri lon nhat");
            System.out.println("3. Thong ke so hoa don theo khoang ngay");
            System.out.println("4. Thong ke tong doanh thu theo khoang ngay");
            System.out.println("5. Thoat");
            System.out.println("=====================================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    totalRevenue();
                    break;
                case 2:
                    searchMaxInvoice();
                    break;
                case 3:
                    totalInvoiceByRangeDate(sc);
                    break;
                case 4:
                    totalAmountByRangeDate(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }

    public static void totalRevenue() {
        double totalAmount = 0;
        for (int i = 0; i < currentInvoiceIndex; i++) {
            totalAmount += arrInvoice[i].getTotalAmount();
        }
        System.out.println("Tong doanh thu cua tat ca hoa don: " + totalAmount);
    }

    public static void searchMaxInvoice(){
        Invoice max = arrInvoice[0];
        Invoice maxInvoice = new Invoice();
        for (int i = 1; i < currentInvoiceIndex; i++) {
            if (arrInvoice[i].getTotalAmount() > max.getTotalAmount()) {
                max = arrInvoice[i];
                maxInvoice = arrInvoice[i];
            }
        }
        System.out.println("Hoa don co gia tri lon nhat: ");
        maxInvoice.displayData();
    }

    public static void totalInvoiceByRangeDate(Scanner sc) {
        System.out.print("Nhap khoang ngay thu nhat(yyyy-MM-dd): ");
        Date startDate = Date.valueOf(sc.nextLine());;
        System.out.print("Nhap khoang ngay thu hai(yyyy-MM-dd): ");
        Date endDate = Date.valueOf(sc.nextLine());
        boolean isFound = false;
        for (int i = 0; i < currentInvoiceIndex; i++) {
            if (arrInvoice[i].getInvoiceDate().after(startDate) && arrInvoice[i].getInvoiceDate().before(endDate)){
                arrInvoice[i].displayData();
                isFound = true;
            }
        }
        if (!isFound) {
            System.err.println("Khong co hoa don");
        }
    }

    public static void totalAmountByRangeDate(Scanner sc) {
        System.out.print("Nhap khoang ngay thu nhat(yyyy-MM-dd): ");
        Date startDate = Date.valueOf(sc.nextLine());;
        System.out.print("Nhap khoang ngay thu hai(yyyy-MM-dd): ");
        Date endDate = Date.valueOf(sc.nextLine());
        double totalAmount = 0;
        for (int i = 0; i < currentInvoiceIndex; i++) {
            if (arrInvoice[i].getInvoiceDate().after(startDate) && arrInvoice[i].getInvoiceDate().before(endDate)){
                totalAmount += arrInvoice[i].getTotalAmount();
            }
        }
        System.out.printf("Tong doanh thu tu ngay %s den ngay %s la %.2f\n", startDate, endDate, totalAmount);
    }

}
