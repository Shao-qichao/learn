// 全职员工：拿固定月薪
public class FullTimeEmployee extends Employee {
    protected double monthlySalary;

    public FullTimeEmployee(String name, String id, double monthlySalary) {
        super(name, id, "全职员工");
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calcSalary() {
        // TODO 1：全职员工直接返回月薪 monthlySalary
        return 0.0;
    }
}
