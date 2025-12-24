package Session11.gioi1;

public class Main {
    static Employee[] employees = new Employee[10];
    static int currentIndex = 0;

    public static void main(String[] args) {
        employees[currentIndex++] = new FullTimeEmployee(1, "Trung", 3000000, 1.5);
        employees[currentIndex++] = new FullTimeEmployee(2, "Hung", 4500000, 2.0);
        employees[currentIndex++] = new PartTimeEmployee(3, "Manh", 30000, 160);
        employees[currentIndex++] = new PartTimeEmployee(4, "Dung", 25000, 120);

        for (int i = 0; i < currentIndex; i++) {
            employees[i].showInfo();
            System.out.println("Luong: " + employees[i].calculateSalary());

            if (employees[i] instanceof BonusEligible) {
                System.out.println("Thuong: " + ((BonusEligible) employees[i]).calculateBonus());
            }
        }
    }
}
