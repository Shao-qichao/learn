public class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }

    @Override
    public void showPay() {
        System.out.println(name + "（全职）月薪：" + monthlySalary + " 元");
    }
}
