/*
 * ============================================================
 *  7-3 向上转型与多态   （板块 c7s3 / 第 7 章）
 *  练习项目：ch07-s03-polymorphism-pay
 *  目标：理解向上转型与多态：父类引用指向子类对象，调用被重写的方法时按真实对象执行。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 用父类引用把兼职员工放进 staff[1]
        // - TODO 2 用增强 for 遍历数组并调用 showPay()，观察多态效果
        // - TODO 3 用 instanceof 判断真实类型，向下转型后打印兼职员工的月工时

        Employee[] staff = new Employee[2];
        staff[0] = new FullTimeEmployee("小明", 8000);
        staff[0].showPay();
        System.out.println("数组长度：" + staff.length);

        // TODO 1：往 staff[1] 放入兼职员工对象：new PartTimeEmployee("小红", 50, 20)
        // TODO 2：用增强 for 遍历 staff，对每个元素调用 showPay()，体会"同一句指令、不同反应"
        // TODO 3：用 if (staff[1] instanceof PartTimeEmployee) 判断后向下转型为 PartTimeEmployee，
        //         打印这位兼职员工的月工时 getHours()
    }
}
