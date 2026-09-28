/*
 * ============================================================
 *  7-2 方法重写   （板块 c7s2 / 第 7 章）
 *  练习项目：ch07-s02-override-showpay
 *  目标：掌握方法重写的写法与 @Override 的作用，会在子类里改写继承来的方法并复用 super。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 创建全职员工（姓名、月薪）并调用 showPay()，确认执行子类重写版本
        // - TODO 2 创建兼职员工（姓名、时薪、工时）并调用 showPay()，确认工资 = 时薪 × 工时
        // - TODO 3 修改全职员工月薪后再次调用 showPay()，观察同一方法名在不同类里的不同表现

        Employee e = new Employee();
        e.setName("普通员工");
        e.showPay();

        // TODO 1：new 一个 FullTimeEmployee，setName("小明")、setMonthlySalary(8000.0) 后调用 showPay()
        //         观察执行的是父类版本还是子类重写版本
        // TODO 2：new 一个 PartTimeEmployee，setName("小红")、setHourSalary(50.0)、setHours(20)，
        //         调用 showPay()，确认工资按 时薪 × 工时 计算
        // TODO 3：把全职员工的月薪改成 12000.0 后再调用一次 showPay()，体会同名方法在不同类里表现不同
    }
}
