public class FullTimeEmployee implements Payable {
    private String name;
    private double monthlySalary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }

    @Override
    public double calcSalary() {
        return monthlySalary;
    }
}
