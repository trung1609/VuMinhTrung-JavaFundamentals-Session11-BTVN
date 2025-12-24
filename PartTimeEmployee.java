package Session11.gioi1;

public class PartTimeEmployee extends Employee implements BonusEligible {
    private int workingHour;
    private double luongTheoGio;

    public PartTimeEmployee(int id, String name, double luongTheoGio, int workingHour) {
        super(id, name);
        this.workingHour = workingHour;
        this.luongTheoGio = luongTheoGio;
    }

    @Override
    public double calculateBonus() {
        if(workingHour > 150){
            return 500000;
        }
        return 0;
    }

    @Override
    public double calculateSalary() {
        return luongTheoGio * workingHour;
    }
}
