/*
 * ============================================================
 *  3-3 switch 选择   （板块 c3s3 / 第 3 章）
 *  练习项目：ch03-s03-switch-select
 *  目标：用 switch 按菜单编号做多路选择，并记住每个 case 都要写 break 防穿透。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补上 case 2 打印“存款”、case 3 打印“取款”，每个 case 结尾都要写 break。
        // - 声明 String day = "周六"，用 switch 让“周六”“周日”都打印“睡个懒觉”，其余打印“早起上课”。
        // - 把 choice 改成 4 运行一次，确认输出“无效选项”（走 default）。

        int choice = 2;

        switch (choice) {
            case 1:
                System.out.println("查询余额");
                break;
            default:
                System.out.println("无效选项");
                break;
        }
    }
}
