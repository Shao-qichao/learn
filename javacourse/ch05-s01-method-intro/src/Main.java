/*
 * ============================================================
 *  5-1 方法是什么   （板块 c5s1 / 第 5 章）
 *  练习项目：ch05-s01-method-intro
 *  目标：理解方法就是有名字的一段代码，能把重复代码抽成方法并反复调用。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 把重复的两行欢迎语抽成方法：在类里面、main 外面写 public static void printWelcome() { ... }
        // - 在 main 里用 printWelcome(); 调用两次，把原来的六行 println 替换掉
        // - 再定义 printLine() 只负责打印一行分隔线，并在 main 里调用它

        System.out.println("欢迎光临！");
        System.out.println("今天也要加油哦");
        System.out.println("----------");

        System.out.println("欢迎光临！");
        System.out.println("今天也要加油哦");
        System.out.println("----------");
    }
}
