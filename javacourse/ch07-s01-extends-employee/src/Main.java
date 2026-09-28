/*
 * ============================================================
 *  7-1 继承 extends   （板块 c7s1 / 第 7 章）
 *  练习项目：ch07-s01-extends-employee
 *  目标：理解继承的含义与语法，掌握 extends、protected 以及 super(...) 在子类初始化中的作用。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 用三参构造 new FullTimeEmployee("小红", "E002", 9000) 创建第二位员工
        // - TODO 2 调用继承来的 clockIn() 打卡，并打印姓名、工号、月薪
        // - TODO 3 用 setMonthlySalary(12000) 修改小明月薪并打印验证

        FullTimeEmployee f = new FullTimeEmployee();
        f.setName("小明");
        f.setId("E001");
        f.clockIn();
        System.out.println("姓名：" + f.getName() + "，工号：" + f.getId());

        // TODO 1：用 new FullTimeEmployee("小红", "E002", 9000) 创建第二位全职员工，赋给变量 f2
        // TODO 2：调用 f2.clockIn()（这是从父类继承来的方法），再打印它的姓名、工号和月薪
        // TODO 3：调用 f.setMonthlySalary(12000) 修改小明的月薪，打印新的月薪
    }
}
