/*
 * ============================================================
 *  5-2 定义与调用   （板块 c5s2 / 第 5 章）
 *  练习项目：ch05-s02-method-define-call
 *  目标：掌握无参无返回值方法的定义与调用，用方法复用打印分割线，理解方法必须与 main 平级。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 把 Tools.printLine() 的方法体改成打印一行 20 个等号（即 20 个 = 号）
        // - 在 main 最前面补一句 Tools.printLine();，让标题上方也有分割线
        // - 在 Tools 中新增 printHeader()：先调用 printLine()、再打印“学生管理系统”、再调用 printLine()，并在 main 中调用它

        System.out.println("成绩单标题");
        Tools.printLine();
        System.out.println("小明：90分");
    }
}
