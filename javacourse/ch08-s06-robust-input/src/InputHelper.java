import java.util.Scanner;
import java.util.InputMismatchException;

public class InputHelper {

    // 保证得到一个合法整数：不拿到就不出方法
    public static int inputInt(Scanner sc, String tip) {
        System.out.print(tip);
        // TODO 1：改成 while (true) + try-catch：
        //   try { return sc.nextInt(); }
        //   catch (InputMismatchException e) { 打印 "输入无效，请输入整数！"; sc.next(); }
        return 0;
    }

    // 保证得到一个 min~max 之间的菜单选项
    public static int inputChoice(Scanner sc, int min, int max) {
        // TODO 2：循环调用 inputInt，n 在 [min, max] 内才 return，
        //   否则打印 "没有这个选项，请重新输入"
        return max;
    }
}
