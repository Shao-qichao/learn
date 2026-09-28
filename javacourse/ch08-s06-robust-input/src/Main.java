/*
 * ============================================================
 *  8-6 让输入更健壮   （板块 c8s6 / 第 8 章）
 *  练习项目：ch08-s06-robust-input
 *  目标：用 while 循环加 try-catch 封装 inputInt/inputChoice，让菜单输入怎么输错都不崩溃。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：补全 InputHelper.inputInt——while(true) 里 try { return sc.nextInt(); }，catch (InputMismatchException e) 里提示后必须 sc.next()
        // - TODO 2：补全 InputHelper.inputChoice——循环调用 inputInt，数字在 min~max 之间才 return，否则提示重输
        // - TODO 3：把 main 里写死的 choice 换成 InputHelper.inputChoice(sc, 1, 4)，并故意输入字母和 9 测试

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.添加 2.查看 3.删除 4.退出");
            // TODO 3：把下面这行换成 int choice = InputHelper.inputChoice(sc, 1, 4);
            int choice = 4;
            switch (choice) {
                case 1:
                    System.out.println("执行 添加");
                    break;
                case 2:
                    System.out.println("执行 查看");
                    break;
                case 3:
                    System.out.println("执行 删除");
                    break;
                case 4:
                    System.out.println("再见");
                    return;
                default:
                    System.out.println("没有这个选项，请重新输入");
            }
        }
    }
}
