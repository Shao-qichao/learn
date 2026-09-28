public class Employee {
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

    public void clockIn() {
        System.out.println(name + "(" + id + ") 打卡成功");
    }
}
