/*
 * ============================================================
 *  6-6 章项目：图书管理 v1   （板块 c6s6 / 第 6 章）
 *  练习项目：ch06-s06-book-management
 *  目标：综合运用封装、构造方法与对象数组，完成图书管理 v1 的添加、查看与统计功能。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 用 while(true) + switch 搭好菜单循环，选 4 时打印再见并退出
        // - TODO 2 完成添加分支：录入三项后用 books[count] = new Book(...) 存书并让 count 加一
        // - TODO 3 完成查看与统计分支：只遍历 0 到 count 输出列表，并算出总价和平均价格

        Book[] books = new Book[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("====== 图书管理系统 v1 ======");
        System.out.println("1.添加图书  2.查看所有图书  3.统计信息  4.退出");

        // TODO 1：用 while (true) 包住菜单，每轮打印"请选择："并用 sc.nextInt() 读入选项
        // TODO 2：分支 1 —— 依次读入书名、作者、价格，books[count] = new Book(title, author, price); count++；
        //         打印"《书名》添加成功，当前共 X 本"；count 已经到 100 时提示"书架已满"
        // TODO 3：分支 2 遍历 0~count 调用 showInfo()（count 为 0 时提示"书架是空的"）；
        //         分支 3 统计图书总数、总价、平均价格；分支 4 打印"再见！"并 break

        System.out.println("（提示：请按上面 TODO 补全菜单循环后再运行）");
    }
}
