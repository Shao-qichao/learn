public class Manager implements Payable, Meeting {
    private String name;
    private double monthlySalary;

    public Manager() {
    }

    public Manager(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public double calcSalary() {
        return monthlySalary;
    }

    @Override
    public void meeting() {
        System.out.println(name + " 通知：下午 3 点开部门会议");
    }
}
