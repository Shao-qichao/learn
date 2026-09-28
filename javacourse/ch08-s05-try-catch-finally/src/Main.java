/*
 * ============================================================
 *  8-5 try-catch   （板块 c8s5 / 第 8 章）
 *  练习项目：ch08-s05-try-catch-finally
 *  目标：掌握 try-catch-finally 语法、多个 catch 子类在前的顺序，以及 finally 必定执行的特点。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：用 try-catch 接住 arr[5] 的 ArrayIndexOutOfBoundsException，并打印 e.getMessage()
        // - TODO 2：再写一个 try-catch 接住 100 / 0 的 ArithmeticException，打印“不能输入 0！”
        // - TODO 3：给其中一个 try 加 finally 块打印“finally 一定执行”，验证无论是否出错都会输出

        int[] arr = {1, 2, 3};
        // TODO 1：用 try 包住 System.out.println(arr[5]);，catch (ArrayIndexOutOfBoundsException e) 打印 "下标越界了：" + e.getMessage()

        int n = 0;
        // TODO 2：用 try-catch 包住 System.out.println(100 / n);，catch (ArithmeticException e) 打印 "不能输入 0！"

        // TODO 3：给上面的 try 加一个 finally 块，打印 "finally 一定执行"，观察出错与不出错都会输出

        System.out.println("try-catch 练习未完成，请补全 TODO 1~3 后重新运行");
    }
}
