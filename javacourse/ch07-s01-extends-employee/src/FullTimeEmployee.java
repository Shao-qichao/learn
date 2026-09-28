public class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, String id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }
}
