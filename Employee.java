package Session11.xuatsac1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner sc, Employee[] arrEmp, int index) {
        this.employeeId = inputEmployeeId(sc, arrEmp, index);
        this.employeeName = inputEmployeeName(sc);
        inputRole(sc);
        this.salary = inputSalary(sc);
    }

    public String inputEmployeeId(Scanner sc, Employee[] arrEmp, int index) {
        String regex = "[E]\\d{4}";
        boolean isTrue = false;
        do {
            System.out.print("Nhap ma nhan vien: ");
            String employee_id = sc.nextLine();
            if (Pattern.matches(regex, employee_id)) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrEmp[i].getEmployeeId().equalsIgnoreCase(employee_id)) {
                        System.err.println("Ma nhan vien da ton tai.");
                        ;
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return employee_id;
                }
                isTrue = true;
            }
            if (!isTrue) {
                System.err.println("Vui long nhap lai ma nhan vien.");
            }
        } while (true);
    }

    public String inputEmployeeName(Scanner sc) {
        do {
            System.out.print("Nhap ten nhan vien: ");
            String name = sc.nextLine();
            if (name.length() > 6 && name.length() < 30) {
                return name;
            }
            System.err.println("Vui long nhap lai ten nhan vien.");
        } while (true);
    }
    public boolean checkRole(String input) {
        return input.equals("PM")
                || input.equals("BA")
                || input.equals("DEV")
                || input.equals("TESTER");
    }
    public void inputRole(Scanner sc) {
        String input;
        do {
            System.out.print("Nhap vi tri nhan vien: ");
            input = sc.nextLine().toUpperCase();
            if (!checkRole(input)) {
                System.err.println("Vui long nhap lai vi tri nhan vien.");
            }
        } while (!checkRole(input));
        this.role = Role.valueOf(input);
    }

    public double inputSalary(Scanner sc) {
        do {
            System.out.print("Nhap luong nhan vien: ");
            double salary_emp = Double.parseDouble(sc.nextLine());
            if (salary_emp > 0) {
                return salary_emp;
            }
            System.err.println("Vui long nhap lai luong nhan vien.");
        } while (true);
    }

    public void displayData() {
        System.out.println("-----------------------------------");
        System.out.println("Ma nhan vien: " + this.employeeId);
        System.out.println("Ten nhan vien: " + this.employeeName);
        System.out.println("Vi tri: " + this.role);
        System.out.println("Luong: " + this.salary);
    }
}
