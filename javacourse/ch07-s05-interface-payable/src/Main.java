/*
 * ============================================================
 *  7-5 接口   （板块 c7s5 / 第 7 章）
 *  练习项目：ch07-s05-interface-payable
 *  目标：掌握接口的定义与 implements 实现，理解接口引用多态以及一个类可实现多个接口。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 创建 Manager 对象
        // - TODO 2 用 Payable[] 数组装入两个实现类对象，遍历调用 calcSalary()
        // - TODO 3 把 Manager 对象赋给 Meeting 变量，调用 meeting()

        Payable p = new FullTimeEmployee("小明", 8000);
        System.out.println("接口引用调用的工资：" + p.calcSalary() + " 元");

        // TODO 1：创建经理对象：new Manager("老张", 15000)
        // TODO 2：用 Payable[] 数组装入全职员工和经理两个对象，遍历调用 calcSalary() 并逐一打印
        // TODO 3：把经理对象赋给 Meeting 类型的变量，调用 meeting() 打印会议通知
    }
}
