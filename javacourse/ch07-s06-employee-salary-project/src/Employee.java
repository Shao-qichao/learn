// 抽象父类：只定规矩，不算具体工资
public abstract class Employee {
    protected String name;
    protected String id;
    protected String type;   // 员工类型，由子类通过 super(...) 传入

    public Employee(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() { return name; }
    public String getId() { return id; }

    public void showInfo() {
        System.out.print(name + "(" + id + ") " + type + "  ");
    }

    // 抽象方法：子类必须各自实现
    public abstract double calcSalary();
}
