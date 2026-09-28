/*
 * ============================================================
 *  6-4 构造方法与 this   （板块 c6s4 / 第 6 章）
 *  练习项目：ch06-s04-constructor-this
 *  目标：学会定义构造方法，用 this 区分成员变量与参数，并用 this(...) 复用重载构造。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 用单参构造 new Student("小红") 创建 s2，打印它的姓名和年龄
        // - TODO 2 用全参构造 new Student("小刚", 20) 创建 s3，打印它的姓名和年龄
        // - TODO 3 用 setter 修改 s1 的姓名和年龄，再打印一次验证修改成功

        System.out.println("===== 构造方法与 this 练习 =====");
        // 下面这行用无参构造创建对象（默认值来自 Student() 里的 this("未命名", 0)）
        Student s1 = new Student();
        System.out.println("无参构造 -> " + s1.getName() + "，" + s1.getAge() + " 岁");

        // TODO 1：用单参构造 new Student("小红") 创建 s2，打印它的姓名和年龄
        // TODO 2：用全参构造 new Student("小刚", 20) 创建 s3，打印它的姓名和年龄
        // TODO 3：用 setter 把 s1 的姓名改成"小美"、年龄改成 19，再打印一次 s1
    }
}
