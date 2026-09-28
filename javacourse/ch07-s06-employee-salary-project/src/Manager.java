// 经理也是全职员工：月薪 + 奖金
public class Manager extends FullTimeEmployee {
    private double bonus;

    public Manager(String name, String id, double monthlySalary, double bonus) {
        super(name, id, monthlySalary);   // 先让爸爸把全职员工那部分初始化好
        this.bonus = bonus;
        this.type = "经理";               // 覆盖父类构造里设的类型
    }

    @Override
    public double calcSalary() {
        // TODO 1：先用 super.calcSalary() 拿到月薪，再加上 bonus
        return 0.0;
    }
}
