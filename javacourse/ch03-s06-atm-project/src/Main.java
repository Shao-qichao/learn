/*
 * ============================================================
 *  3-6 章项目：迷你ATM   （板块 c3s6 / 第 3 章）
 *  练习项目：ch03-s06-atm-project
 *  目标：综合使用 if/else、switch、while 和 Scanner，做出能反复存取款、可退出的迷你 ATM。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 用 while 循环包住密码输入，反复提示直到输入 123456；再加一个错误次数计数器，错 3 次直接结束程序
        // - 把 if (choice == 1) 改成 switch，依次实现查询余额、存款（余额 += 金额）、取款（先判断余额是否足够，不够就提示余额不足，取款失败）、退出
        // - 用 while (true) 把菜单包起来实现反复操作，选 4 时用 break 退出并打印感谢使用，再见！

        Scanner sc = new Scanner(System.in);
        double balance = 1000.0;   // 初始余额

        // 登录：只判断一次，还没循环
        System.out.print("请输入密码：");
        int pwd = sc.nextInt();
        if (pwd == 123456) {
            System.out.println("登录成功！");
        } else {
            System.out.println("密码错误，请重新输入");
        }

        // 菜单：只显示一次，还没循环
        System.out.println("====== ATM 菜单 ======");
        System.out.println("1.查询余额  2.存款  3.取款  4.退出");
        System.out.print("请选择功能：");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("当前余额：" + balance + " 元");
        } else {
            System.out.println("该功能待实现");
        }
    }
}
