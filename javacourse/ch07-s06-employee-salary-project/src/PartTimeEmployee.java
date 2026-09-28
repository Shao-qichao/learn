// 兼职员工：按小时结算
public class PartTimeEmployee extends Employee {
    protected double hourSalary;
    protected int hours;

    public PartTimeEmployee(String name, String id, double hourSalary, int hours) {
        super(name, id, "兼职员工");
        this.hourSalary = hourSalary;
        this.hours = hours;
    }

    @Override
    public double calcSalary() {
        // TODO 1：时薪 × 月工时
        return 0.0;
    }
}
