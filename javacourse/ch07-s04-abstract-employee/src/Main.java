/*
 * ============================================================
 *  7-4 抽象类   （板块 c7s4 / 第 7 章）
 *  练习项目：ch07-s04-abstract-employee
 *  目标：掌握抽象类与抽象方法的规则，理解父类定规矩、子类兑现的设计，并用多态数组做统计。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 往 staff[2] 再放入一位兼职员工对象
        // - TODO 2 用增强 for 遍历数组，调用 showInfo() 并累加 calcSalary()
        // - TODO 3 输出工资合计与平均工资（注意平均工资是小数）

        Employee[] staff = new Employee[3];
        staff[0] = new FullTimeEmployee("小明", "E001", 8000);
        staff[1] = new PartTimeEmployee("小红", "E002", 50, 20);
        System.out.println("第 1 位：" + staff[0].getName() + "，工资 " + staff[0].calcSalary() + " 元");

        // TODO 1：给 staff[2] 再放一位兼职员工，例如 new PartTimeEmployee("小刚", "E003", 40, 30)
        // TODO 2：用增强 for 遍历数组，调用 showInfo() 打印姓名工号，并把 calcSalary() 累加到 total
        // TODO 3：输出工资合计与平均工资（合计除以 staff.length，结果是小数）
    }
}
