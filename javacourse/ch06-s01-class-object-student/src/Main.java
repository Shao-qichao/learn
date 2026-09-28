/*
 * ============================================================
 *  6-1 类与对象   （板块 c6s1 / 第 6 章）
 *  练习项目：ch06-s01-class-object-student
 *  目标：理解类与对象的关系，会定义类、用 new 创建对象并通过点访问属性和成员方法。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 在 main 中再 new 一个 Student 对象 s2，把 name 设为“小红”、age 设为 17，并调用 s2.study()
        // - 在 Student 类中新增方法 introduce()，打印“我叫 XX，今年 XX 岁”（XX 用成员变量 name、age 拼接）
        // - 在 main 中分别调用 s1.introduce() 和 s2.introduce()，确认每个对象打印的是自己的数据

        Student s1 = new Student();
        s1.name = "小明";
        s1.age = 18;
        s1.study();
    }
}
