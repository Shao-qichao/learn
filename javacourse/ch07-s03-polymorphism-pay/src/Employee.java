public class Employee {
    protected String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void showPay() {
        System.out.println(name + " 的工资按通用规则计算");
    }
}
