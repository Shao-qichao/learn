public abstract class Employee {
    protected String name;
    protected String id;

    public Employee() {
    }

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public void showInfo() {
        System.out.println("姓名：" + name + "，工号：" + id);
    }

    public abstract double calcSalary();
}
