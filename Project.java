package Session11.xuatsac1;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees;
    private Status status;

    public Project() {
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, Employee[] employees, Status status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void inputData(Scanner sc, Project[] arrProject, int index, Employee[] arrEmp, int empIndex) {
        this.projectId = inputProjectId(sc, arrProject, index);
        this.projectName = inputProjectName(sc, arrProject, index);
        this.startDate = inputStartDate(sc);
        this.endDate = inputEndDate(sc);
        this.employees =  inputEmployee(sc, arrEmp, empIndex);
        inputStatus(sc);
    }

    public String inputProjectId(Scanner sc, Project[] arrProject, int index) {
        String regex = "[P]\\d{4}";
        boolean isTrue = false;
        do {
            System.out.print("Nhap ma du an: ");
            String projectId = sc.nextLine();
            if (Pattern.matches(regex, projectId)) {
                boolean isExists = false;
                for (int i = 0; i < index; i++) {
                    if (arrProject[i].getProjectId().equalsIgnoreCase(projectId)) {
                        System.err.println("Ma du an da ton tai.");
                        isExists = true;
                        break;
                    }
                }
                if (!isExists) {
                    return projectId;
                }
                isTrue = true;
            }
            if (!isTrue) {
                System.err.println("Vui long nhap lai ma du an.");
            }
        } while (true);
    }

    public String inputProjectName(Scanner sc, Project[] arrProject, int index) {
        do {
            System.out.print("Nhap ten du an: ");
            String projectName = sc.nextLine();
            if (projectName.length() < 10 || projectName.length() > 50) {
                System.err.println("Vui long nhap lai ten du an.");
                continue;
            }
            boolean isExists = false;
            for (int i = 0; i < index; i++) {
                if (arrProject[i].getProjectName().equalsIgnoreCase(projectName)) {
                    System.err.println("Ten du an da ton tai");
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                return projectName;
            }

        } while (true);
    }

    public LocalDate inputStartDate(Scanner sc) {
        LocalDate startDate;
        do {
            System.out.print("Nhap ngay bat dau du an(YYYY-MM-DD): ");
            String input = sc.nextLine();
            if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                startDate = LocalDate.parse(input);
                return startDate;
            } else {
                System.err.println("Vui long nhap lai dung dinh dang ngay.");
            }
        } while (true);
    }

    public LocalDate inputEndDate(Scanner sc) {
        LocalDate endDate;
        do {
            System.out.print("Nhap ngay ket thuc du an(YYYY-MM-DD): ");
            String input = sc.nextLine();
            if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                endDate = LocalDate.parse(input);
                if (!endDate.isBefore(startDate)) {
                    return endDate;
                } else {
                    System.err.println("Vui long nhap ngay ket thuc du an lon hon hoac bang ngay bat dau du an.");
                }
            }
        } while (true);
    }

    public Employee[] inputEmployee(Scanner sc, Employee[] arrEmp, int empIndex) {
        System.out.println("--- Danh Sach Nhan Vien ---");
        if(empIndex == 0){
            System.out.println("Chua co nhan vien duoc them");
        }else {
            for (int i = 0; i < empIndex; i++) {
                System.out.printf("Nhan vien thu %d: \n", i + 1);
                System.out.println("Ma nhan Vien: " + arrEmp[i].getEmployeeId() + " - Ten nhan vien: " + arrEmp[i].getEmployeeName());
            }
            do {
                System.out.print("Chon ma nhan vien vao du an: ");
                String employeeId = sc.nextLine();
                for (int i = 0; i < empIndex; i++) {
                    if (arrEmp[i].getEmployeeId().equalsIgnoreCase(employeeId)) {
                        return new Employee[]{arrEmp[i]};
                    }
                }
                System.err.println("Ma nhan vien khong ton tai.");
            } while (true);
        }
        return null;
    }

    public void inputStatus(Scanner sc) {
        String status;
        do {
            System.out.print("Nhap trang thai du an: ");
            status = sc.nextLine().toUpperCase();
            if (!status.equals("PLANNING") && !status.equals("RUNNING") && !status.equals("FINISHED")) {
                System.err.println("Vui long nhap lai trang thai du an.");
            }
        } while (!status.equals("PLANNING") && !status.equals("RUNNING") && !status.equals("FINISHED"));
        this.status = Status.valueOf(status);
    }

    public void displayData() {
        System.out.println("-----------------------------------");
        System.out.println("Ma du an: " + this.projectId);
        System.out.println("Ten du an: " + this.projectName);
        System.out.println("Ngay bat dau: " + this.startDate.toString());
        System.out.println("Ngay ket thuc: " + this.endDate.toString());
        if (employees == null) {
            System.out.println("Chua co nhan vien tham gia");
        } else {
            System.out.println("Thong tin nhan vien: ");
            for (Employee e : employees) {
                System.out.println("- " + e.getEmployeeId() + " | " + e.getEmployeeName());
            }
        }
        System.out.println("Trang thai: " + this.status);
    }
}
