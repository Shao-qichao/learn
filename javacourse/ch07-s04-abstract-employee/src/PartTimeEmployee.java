public class PartTimeEmployee extends Employee {
    private double hourSalary;
    private int hours;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, String id, double hourSalary, int hours) {
        super(name, id);
        this.hourSalary = hourSalary;
        this.hours = hours;
    }

    public double getHourSalary() { return hourSalary; }
    public void setHourSalary(double hourSalary) { this.hourSalary = hourSalary; }
    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }

    @Override
    public double calcSalary() {
        return hourSalary * hours;
    }
}
