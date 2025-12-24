package Session11.gioi1;

public class FullTimeEmployee extends Employee implements BonusEligible {
    private double luongCoBan;
    private double heSoLuong;

    public FullTimeEmployee(int id, String name, double luongCoBan, double heSoLuong) {
        super(id, name);
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
    }

    @Override
    public double calculateBonus() {
        return calculateSalary() * 0.1;
    }

    @Override
    public double calculateSalary() {
        return luongCoBan * heSoLuong;
    }
}
