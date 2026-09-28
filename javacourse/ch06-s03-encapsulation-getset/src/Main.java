/*
 * ============================================================
 *  6-3 封装   （板块 c6s3 / 第 6 章）
 *  练习项目：ch06-s03-encapsulation-getset
 *  目标：掌握封装：用 private 藏起属性，通过公开的 get/set 方法访问，并在 setter 里做合法性校验。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补全 Student.setAge(int a)：只有 0 到 150 之间才把 a 赋给 age，否则打印“年龄不合法：”加 a
        // - 补全 Student.setPrice(double p)：只有 p 大于等于 0 才赋值，否则打印“价格不合法”
        // - 尝试在 main 中写 s.age = 20; 观察 private access 编译错误，改回用 setAge 赋值、getAge 读取

        Student s = new Student();
        s.setName("小明");
        s.setAge(-100);
        s.setAge(18);
        System.out.println(s.getName() + "，" + s.getAge());
        s.setPrice(-5);
        System.out.println("价格：" + s.getPrice());
    }
}
