public class PartTimeEmployee extends Employee {
    private double hourSalary;
    private int hours;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, double hourSalary, int hours) {
        super(name);
        this.hourSalary = hourSalary;
        this.hours = hours;
    }

    public double getHourSalary() { return hourSalary; }
    public void setHourSalary(double hourSalary) { this.hourSalary = hourSalary; }
    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }

    @Override
    public void showPay() {
        super.showPay();
        System.out.println(name + "（兼职）时薪 " + hourSalary + " 元，本月 " + hours + " 小时，合计 " + (hourSalary * hours) + " 元");
    }
}
