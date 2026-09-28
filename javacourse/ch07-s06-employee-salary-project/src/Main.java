/*
 * ============================================================
 *  7-6 章项目：薪资系统   （板块 c7s6 / 第 7 章）
 *  练习项目：ch07-s06-employee-salary-project
 *  目标：用抽象类、继承、方法重写和多态数组实现员工薪资系统，理解面向对象三大特性。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：补全三个子类的 calcSalary()——FullTimeEmployee 返回 monthlySalary，PartTimeEmployee 返回 hourSalary * hours，Manager 返回 super.calcSalary() + bonus
        // - TODO 2：在 main 的 for 循环里把占位行换成 System.out.println("工资：" + e.calcSalary())，不要写 instanceof 分支
        // - TODO 3：循环结束后打印 公司本月总薪资：total 元，共 staff.length 人

        Employee[] staff = new Employee[3];
        staff[0] = new FullTimeEmployee("小明", "E001", 8000);
        staff[1] = new PartTimeEmployee("小红", "E002", 50, 20);
        staff[2] = new Manager("老张", "E003", 11000, 2000);

        System.out.println("====== 本月薪资表 ======");
        double total = 0;
        for (Employee e : staff) {
            e.showInfo();
            // TODO 2：把下一行换成 System.out.println("工资：" + e.calcSalary());
            System.out.println("（工资待计算）");
            total += e.calcSalary();
        }
        System.out.println("------------------------");
        // TODO 3：打印 公司本月总薪资：total 元，共 staff.length 人
        System.out.println("薪资统计待完成，请补全 TODO 1~3 后重新运行");
    }
}
