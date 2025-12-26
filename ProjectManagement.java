package Session11.xuatsac1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ProjectManagement {
    static Employee[] arrEmployees = new Employee[100];
    static int currentEmployee = 0;
    static int currentProject = 0;
    static Project[] arrProjects = new Project[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        addData(arrEmployees);
        do {
            System.out.println("=============== QUAN LY DU AN ===============");
            System.out.println("1. Quan ly nhan vien");
            System.out.println("2. Quan ly du an");
            System.out.println("3. Thoat");
            System.out.println("=============================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    employeeManagement(sc);
                    break;
                case 2:
                    projectManagement(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap lai lua chon phu hop");
            }
        } while (true);
    }

    public static void employeeManagement(Scanner sc) {
        do {
            System.out.println("=============== QUAN LY NHAN VIEN ===============");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Hien thi danh sach nhan vien");
            System.out.println("3. Cap nhat thong tin nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Tim kiem nhan vien theo ten");
            System.out.println("6. Sap xep nhan vien theo luong giam dan");
            System.out.println("7. Thoat");
            System.out.println("==================================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    showEmployee();
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    searchEmployeeByName(sc);
                    break;
                case 6:
                    sortEmployeeByPriceDESC();
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui long nhap lai lua chon phu hop");
            }
        } while (true);
    }

    public static void addEmployee(Scanner sc) {
        System.out.print("Nhap so luong nhan vien muon them: ");
        int countEmployee = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < countEmployee; i++) {
            System.out.printf("Nhap thong tin nhan vien thu %d: \n", i + 1);
            arrEmployees[currentEmployee] = new Employee();
            arrEmployees[currentEmployee].inputData(sc, arrEmployees, currentEmployee);
            currentEmployee++;
        }
    }

    public static void showEmployee() {
        if (currentEmployee == 0) {
            System.out.println("Chua co nhan vien duoc them vao.");
        } else {
            for (int i = 0; i < currentEmployee; i++) {
                arrEmployees[i].displayData();
            }
        }
    }

    public static int checkEmpId(String empId) {
        for (int i = 0; i < currentEmployee; i++) {
            if (arrEmployees[i].getEmployeeId().equals(empId)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateEmployee(Scanner sc) {
        if (currentEmployee == 0) {
            System.out.println("Chua co nhan vien duoc them vao.");
        } else {
            System.out.print("Nhap ma nhan vien can cap nhat: ");
            String empId = sc.nextLine();
            int indexUpdate = checkEmpId(empId);
            if (indexUpdate == -1) {
                System.err.println("Ma nhan vien khong ton tai.");
            } else {
                do {
                    System.out.println("Chon thong tin can cap nhat: ");
                    System.out.println("1. Cap nhat ten nhan vien");
                    System.out.println("2. Cap nhat vi tri nhan vien");
                    System.out.println("3. Cap nhat luong nhan vien");
                    System.out.println("4. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            String newEmpName;
                            do {
                                System.out.print("Nhap ten moi: ");
                                newEmpName = sc.nextLine();
                                if (newEmpName.length() < 6 || newEmpName.length() > 30) {
                                    System.err.println("Vui long nhap lai ten nhan vien.");
                                }
                            } while (newEmpName.length() < 6 || newEmpName.length() > 30);
                            arrEmployees[indexUpdate].setEmployeeName(newEmpName);
                            break;
                        case 2:
                            String newRole;
                            do {
                                System.out.print("Nhap vi tri moi: ");
                                newRole = sc.nextLine().toUpperCase();
                                if (!arrEmployees[indexUpdate].checkRole(newRole)) {
                                    System.err.println("Vui long nhap lai vi tri.");
                                }
                            } while (!arrEmployees[indexUpdate].checkRole(newRole));
                            arrEmployees[indexUpdate].setRole(Role.valueOf(newRole));
                            break;
                        case 3:
                            double newPrice;
                            do {
                                System.out.print("Nhap luong moi: ");
                                newPrice = Double.parseDouble(sc.nextLine());
                                if (newPrice < 0) {
                                    System.err.println("Vui long nhap lai luong.");
                                }
                            } while (newPrice < 0);
                            arrEmployees[indexUpdate].setSalary(newPrice);
                            break;
                        case 4:
                            return;
                        default:
                            System.err.println("Vui long nhap lai lua chon phu hop.");
                    }
                } while (true);
            }
        }
    }

    public static void deleteEmployee(Scanner sc) {
        if (currentEmployee == 0) {
            System.out.println("Chua co nhan vien duoc them vao.");
        } else {
            System.out.print("Nhap ma nhan vien can xoa: ");
            String empId = sc.nextLine();
            int indexDelete = checkEmpId(empId);
            if (indexDelete == -1) {
                System.err.println("Ma nhan vien khong ton tai.");
            } else {
                for (int i = indexDelete; i < currentEmployee; i++) {
                    arrEmployees[i] = arrEmployees[i + 1];
                }
                currentEmployee--;
                System.out.println("Xoa thanh cong");
            }
        }
    }

    public static void searchEmployeeByName(Scanner sc) {
        if (currentEmployee == 0) {
            System.out.println("Chua co nhan vien duoc them vao.");
        } else {
            System.out.print("Nhap ten nhan vien can tim: ");
            String empName = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentEmployee; i++) {
                if (arrEmployees[i].getEmployeeName().toLowerCase().contains(empName.toLowerCase())) {
                    arrEmployees[i].displayData();
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay nhan vien");
            }
        }
    }

    public static void sortEmployeeByPriceDESC() {
        if (currentEmployee == 0) {
            System.out.println("Chua co nhan vien duoc them vao.");
        } else {
            for (int i = 0; i < currentEmployee - 1; i++) {
                for (int j = 0; j < currentEmployee - i - 1; j++) {
                    if (arrEmployees[j].getSalary() < arrEmployees[j + 1].getSalary()) {
                        Employee tmp = arrEmployees[j];
                        arrEmployees[j] = arrEmployees[j + 1];
                        arrEmployees[j + 1] = tmp;
                    }
                }
            }
            System.out.println("Da sap xep nhan vien theo luong giam dan thanh cong.");
        }
    }

    public static void projectManagement(Scanner sc) {
        do {
            System.out.println("=============== QUAN LY DU AN ===============");
            System.out.println("1. Them du an");
            System.out.println("2. Hien thi danh sach du an");
            System.out.println("3. Cap nhat thong tin du an");
            System.out.println("4. Xoa du an");
            System.out.println("5. Them nhan vien vao du an");
            System.out.println("6. Tim du an theo ten");
            System.out.println("7. Thong ke so luong nhan vien theo vai tro trong tung du an");
            System.out.println("8. Tim du an dang chay va gan ket thuc nhat");
            System.out.println("9. Thoat");
            System.out.println("==============================================");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProject(sc);
                    break;
                case 2:
                    showProject();
                    break;
                case 3:
                    updateProject(sc);
                    break;
                case 4:
                    deleteProject(sc);
                    break;
                case 5:
                    addEmployeeToProject(sc);
                    break;
                case 6:
                    searchProjectByName(sc);
                    break;
                case 7:
                    statisticEmployeeByRole(sc);
                    break;
                case 8:
                    searchProjectByStatus(sc);
                    break;
                case 9:
                    return;
                default:
                    System.err.println("Vui long nhap lai lua chon phu hop.");
            }
        } while (true);
    }

    public static void addProject(Scanner sc) {
        System.out.print("Nhap so luong du an muon them: ");
        int countProject = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < countProject; i++) {
            System.out.printf("Nhap du an thu %d: \n", i + 1);
            arrProjects[currentProject] = new Project();
            arrProjects[currentProject].inputData(sc, arrProjects, currentProject, arrEmployees, currentEmployee);
            currentProject++;
        }
    }

    public static void showProject() {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            for (int i = 0; i < currentProject; i++) {
                arrProjects[i].displayData();
            }
        }
    }

    public static int checkProjectId(String projectId) {
        for (int i = 0; i < currentProject; i++) {
            if (arrProjects[i].getProjectId().equals(projectId)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateProject(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            System.out.print("Nhap ma du an can cap nhat: ");
            String projectId = sc.nextLine();
            int indexUpdate = checkProjectId(projectId);
            if (indexUpdate == -1) {
                System.err.println("Ma du an khong ton tai");
            } else {
                do {
                    System.out.println("Chon thong tin can cap nhat:");
                    System.out.println("1. Cap nhat ten du an");
                    System.out.println("2. Cap nhat ngay bat dau du an");
                    System.out.println("3. Cap nhat ngay ket thuc du an");
                    System.out.println("4. Cap nhat nhan vien tham gia");
                    System.out.println("5. Cap nhat trang thai");
                    System.out.println("6. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            String newProjectName;
                            do {
                                System.out.print("Nhap ten du an moi: ");
                                newProjectName = sc.nextLine();
                                if (newProjectName.length() < 10 || newProjectName.length() > 50) {
                                    System.err.println("Ten du an phai tu 10 den 50 ky tu.");
                                    continue;
                                }
                                boolean isExists = false;
                                for (int i = 0; i < currentProject; i++) {
                                    if (arrProjects[i].getProjectName().equalsIgnoreCase(newProjectName)) {
                                        isExists = true;
                                        break;
                                    }
                                }
                                if (isExists) {
                                    System.err.println("Ten du an da ton tai");
                                }
                            } while (newProjectName.length() < 10 || newProjectName.length() > 50);
                            arrProjects[indexUpdate].setProjectName(newProjectName);
                            break;
                        case 2:
                            LocalDate newStartDate = null;
                            String input;
                            do {
                                System.out.print("Nhap ngay bat dau du an moi(YYYY-MM-DD): ");
                                input = sc.nextLine();
                                if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    System.err.println("Vui long nhap lai dung dinh dang ngay.");
                                    continue;
                                }
                                newStartDate = LocalDate.parse(input);
                            } while (!input.matches("\\d{4}-\\d{2}-\\d{2}"));
                            arrProjects[indexUpdate].setStartDate(newStartDate);
                            break;
                        case 3:
                            LocalDate newEndDate;
                            String inputEndDate;
                            do {
                                System.out.print("Nhap ngay ket thuc du an moi(YYYY-MM-DD): ");
                                inputEndDate = sc.nextLine();
                                if (!inputEndDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    System.err.println("Vui long nhap dung dinh dang ngay.");
                                    continue;
                                }
                                newEndDate = LocalDate.parse(inputEndDate);
                                if (newEndDate.isBefore(arrProjects[indexUpdate].getStartDate())) {
                                    System.err.println("Vui long nhap ngay ket thuc du an lon hon hoac bang ngay bat dau du an.");
                                    continue;
                                }
                                break;
                            } while (true);
                            arrProjects[indexUpdate].setEndDate(newEndDate);
                            break;
                        case 4:
                            if (currentEmployee == 0) {
                                System.out.println("Chua co nhan vien duoc them");
                                break;
                            }
                            System.out.println("--- Danh Sach Nhan Vien ---");
                            for (int i = 0; i < currentEmployee; i++) {
                                System.out.printf("Nhan vien thu %d: \n", i + 1);
                                System.out.println("Ma nhan Vien: " + arrEmployees[i].getEmployeeId() + " - Ten nhan vien: " + arrEmployees[i].getEmployeeName());
                            }
                            do {
                                System.out.print("Chon ma nhan vien moi vao du an: ");
                                String employeeId = sc.nextLine();
                                boolean isExists = false;
                                for (int i = 0; i < currentEmployee; i++) {
                                    if (arrEmployees[i].getEmployeeId().equalsIgnoreCase(employeeId)) {
                                        arrProjects[indexUpdate].setEmployees(new Employee[]{arrEmployees[i]});
                                        isExists = true;
                                        break;
                                    }
                                }
                                if (!isExists) {
                                    System.err.println("Ma nhan vien khong ton tai.");
                                }
                                break;
                            } while (true);
                            break;
                        case 5:
                            String newStatus;
                            do {
                                System.out.print("Nhap trang thai moi cua du an: ");
                                newStatus = sc.nextLine().toUpperCase();
                                if (!newStatus.equals("PLANNING") && !newStatus.equals("RUNNING") && !newStatus.equals("FINISHED")) {
                                    System.err.println("Vui long nhap lai trang thai du an.");
                                }
                            } while (!newStatus.equals("PLANNING") && !newStatus.equals("RUNNING") && !newStatus.equals("FINISHED"));
                            arrProjects[indexUpdate].setStatus(Status.valueOf(newStatus));
                            break;
                        case 6:
                            return;
                        default:
                            System.err.println("Vui long nhap lai lua chon phu hop.");
                    }
                } while (true);
            }
        }
    }

    public static void deleteProject(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            System.out.print("Nhap ma du an can xoa: ");
            String projectId = sc.nextLine();
            int indexDelete = checkProjectId(projectId);

            if (indexDelete == -1) {
                System.err.println("Ma du an khong ton tai");
            } else {
                Employee[] employees = arrProjects[indexDelete].getEmployees();
                if (employees != null && employees.length > 0) {
                    System.out.println("Khong the xoa du an dang co nhan vien tham gia");
                    return;
                }

                for (int i = indexDelete; i < currentProject; i++) {
                    arrProjects[i] = arrProjects[i + 1];
                }
                currentProject--;
                System.out.println("Xoa du an thanh cong");
            }
        }
    }

    public static void addEmployeeToProject(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            /*
             * Chức năng thêm mới nhân viên vào dự án
             * B1 : hiển thị danh sách dự án cho người dùng xem
             * B2 : Cho người dùng nhập vào id của dự án
             *    + không tìm thấy dự án nào có id như vậy -> in ra thông báo ko tìm thấy
             *    + Nếu tìm thấy : hiển thị danh sách nhân viên hiện có và cho người dùng nhập vào id của nhân viên muốn thêm vào dự án
             *               + kiểm tra xem id nhân viên có tồn tại trong danh sách nv ko ?
             *                   + nếu ko tồn tại thì in ra thông báo => nv ko tồn tại
             *                   + nếu tồn tại :kiểm tra xem nv đã có trong dự án chưa ?
             *                           + tồn tại => in ra thông báo nv đã tồn tại trong dự án
             *                           + chưa tồn tai thì thêm vào project đó.
             *                                  => Tạo ra 1 mảng danh sách Employee vơới độ dài mảng lớn hơn mảng cũ của project 1 phần tử
             *                                     sao chép các phần tử ở mảng Empoyee cũ sang mảng mới
             *                                     => thêm employee mà người dùng vừa nhập vào phần tử cuối cùng trong mảng mới
             *                                          => gán lại danh sách Empoyee = mảng Empoyee mới vừa tạo ra.
             *                                           => in ra thông báo đã thêm thaành công nhân viên vào dự án
             *
             * */

            System.out.println("--- Danh Sach Du An ---");
            for (int i = 0; i < currentProject; i++) {
                System.out.printf("Du an thu %d: \n", i + 1);
                arrProjects[i].displayData();
            }
            System.out.print("Nhap vao ma du an: ");
            String projectId = sc.nextLine();
            int indexAdd = checkProjectId(projectId);
            if (indexAdd == -1) {
                System.err.println("Ma du an khong ton tai");
            } else {
                System.out.println("--- Danh Sach Nhan Vien ---");
                for (int i = 0; i < currentEmployee; i++) {
                    System.out.printf("Nhan vien thu %d: \n", i + 1);
                    arrEmployees[i].displayData();
                }
                System.out.println("1. Them 1 nhan vien");
                System.out.println("2. Them nhieu nhan vien");
                System.out.print("Lua chon cua ban: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhap vao ma nhan vien muon them: ");
                        String employeeId = sc.nextLine();
                        int checkEmployeeId = checkEmpId(employeeId);
                        if (checkEmployeeId == -1) {
                            System.err.println("Ma nhan vien khong ton tai");
                        } else {
                            for (int i = 0; i < arrProjects[indexAdd].getEmployees().length; i++) {
                                if (arrProjects[indexAdd].getEmployees()[i].getEmployeeId().equalsIgnoreCase(employeeId)) {
                                    System.out.println("Nhan vien da ton tai trong du an");
                                    return;
                                }
                            }

                            Employee[] newEmployees = new Employee[arrProjects[indexAdd].getEmployees().length + 1];
                            for (int i = 0; i < arrProjects[indexAdd].getEmployees().length; i++) {
                                newEmployees[i] = arrProjects[indexAdd].getEmployees()[i];
                            }
                            newEmployees[newEmployees.length - 1] = arrEmployees[checkEmployeeId];
                            arrProjects[indexAdd].setEmployees(newEmployees);
                            System.out.println("Them moi 1 nhan vien vao du an thanh cong");
                        }
                        break;
                    case 2:
                        System.out.print("Nhap so luong nhan vien muon them: ");
                        int count = Integer.parseInt(sc.nextLine());
                        if (count > (currentEmployee - arrProjects[indexAdd].getEmployees().length)) {
                            System.out.println("So luong vuot qua so luong nhan vien");
                            return;
                        }
                        for (int i = 0; i < count; i++) {
                            System.out.printf("Nhap vao ma nhan vien thu %d muon them: ", i + 1);
                            String employeeId2 = sc.nextLine();
                            int checkEmployeeId2 = checkEmpId(employeeId2);
                            if (checkEmployeeId2 == -1) {
                                System.err.println("Ma nhan vien khong ton tai");
                            } else {
                                for (int j = 0; j < arrProjects[indexAdd].getEmployees().length; j++) {
                                    if (arrProjects[indexAdd].getEmployees()[j].getEmployeeId().equalsIgnoreCase(employeeId2)) {
                                        System.out.println("Nhan vien da ton tai trong du an");
                                        return;
                                    }
                                }

                                Employee[] newEmployees = new Employee[arrProjects[indexAdd].getEmployees().length + 1];
                                for (int k = 0; k < arrProjects[indexAdd].getEmployees().length; k++) {
                                    newEmployees[k] = arrProjects[indexAdd].getEmployees()[k];
                                }
                                newEmployees[newEmployees.length - 1] = arrEmployees[checkEmployeeId2];
                                arrProjects[indexAdd].setEmployees(newEmployees);
                            }
                        }
                        System.out.printf("Them moi %d nhan vien vao du an thanh cong\n", count);
                        break;
                    default:
                        System.err.println("Vui long nhap lai lua chon phu hop.");
                }
            }
        }
    }

    public static void searchProjectByName(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            System.out.print("Nhap ten du an can tim: ");
            String projectName = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentProject; i++) {
                if (arrProjects[i].getProjectName().toLowerCase().contains(projectName.toLowerCase())) {
                    arrProjects[i].displayData();
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay du an.");
            }
        }
    }

    public static void statisticEmployeeByRole(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            for (int i = 0; i < currentProject; i++) {
                System.out.println("--- Danh Sach Nhan Vien Cua Du An " + arrProjects[i].getProjectName() + " ---");
                for (Role r : Role.values()) {
                    int count = countEmployeeByRole(arrProjects[i].getEmployees(), r);
                    if (count > 0) {
                        System.out.printf("Co %d nguoi co role: %s\n", countEmployeeByRole(arrProjects[i].getEmployees(), r), r.toString());
                    }
                }
            }
        }
    }

    public static int countEmployeeByRole(Employee[] employees, Role role) {
        int count = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getRole() == role) {
                count++;
            }
        }
        return count;
    }

    public static void addData(Employee[] employees) {
        employees[0] = new Employee("E1000", "Nguyen Van A", Role.DEV, 2000);
        employees[1] = new Employee("E1001", "Nguyen Van B", Role.PM, 2002);
        employees[2] = new Employee("E1002", "Nguyen Van C", Role.DEV, 2230);
        employees[3] = new Employee("E1003", "Nguyen Van D", Role.TESTER, 4000);
        employees[4] = new Employee("E1004", "Nguyen Van E", Role.BA, 5400);
        employees[5] = new Employee("E1005", "Nguyen Van F", Role.DEV, 3200);
        currentEmployee += 6;
    }

    public static void searchProjectByStatus(Scanner sc) {
        if (currentProject == 0) {
            System.out.println("Chua co du an nao duoc them");
        } else {
            System.out.println("--- Danh Sach Du An Dang Chay Va Gan Ket Thuc Nhat ---");
            for (Status s : Status.values()) {
                if (s.equals(Status.RUNNING)) {
                    System.out.println("Status: " + s);
                    printProjectByStatusAndNearEndDate(currentProject, s);
                    System.out.println();
                }
            }
        }
    }


    public static void printProjectByStatusAndNearEndDate(int currentIndex, Status status) {
        long minDay = Long.MAX_VALUE;

        for (int i = 0; i < currentIndex; i++) {
            long day = ChronoUnit.DAYS.between(LocalDate.now(), arrProjects[i].getEndDate());
            if (arrProjects[i].getStatus() == status) {
                if (day >= 0 && day < minDay) {
                    minDay = day;
                }
            }
        }
        for (int i = 0; i < currentIndex; i++) {
            long day = ChronoUnit.DAYS.between(LocalDate.now(), arrProjects[i].getEndDate());
            if (day == minDay) {
                arrProjects[i].displayData();
            }
        }
    }

}
