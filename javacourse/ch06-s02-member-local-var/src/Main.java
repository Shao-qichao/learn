/*
 * ============================================================
 *  6-2 成员变量与方法   （板块 c6s2 / 第 6 章）
 *  练习项目：ch06-s02-member-local-var
 *  目标：区分成员变量与局部变量，会写能直接使用成员变量的成员方法，并在 main 中通过对象调用。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 在 Account 中新增 withdraw(double money) 方法：余额够就扣减并打印“XX 取款 YY，余额 ZZ”，不够则打印“余额不足”
        // - 在 main 中依次调用 acc.withdraw(200) 和 acc.withdraw(1000)，观察两次输出的不同
        // - 验证：在 main 里直接写 System.out.println(balance); 为什么会编译报错？改成用 acc.balance 访问后再运行

        Account acc = new Account();
        acc.owner = "小明";
        acc.deposit(500);
        System.out.println("余额：" + acc.getBalance());
    }
}
