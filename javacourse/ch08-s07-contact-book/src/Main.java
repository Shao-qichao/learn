/*
 * ============================================================
 *  8-7 章项目：通讯录 v1   （板块 c8s7 / 第 8 章）
 *  练习项目：ch08-s07-contact-book
 *  目标：综合封装类、ArrayList 增删改查、String 模糊搜索和 try-catch 容错，独立完成通讯录 v1。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：把菜单选择换成 while(true)+try-catch 的健壮输入（catch InputMismatchException 时提示并 sc.next()），并校验数字在 1~6 之间
        // - TODO 2：实现 1 添加（姓名重复用 equals 判断提示“已存在”）和 2 查看（带序号遍历，空通讯录提示“还没有联系人”）
        // - TODO 3：实现 3 搜索（getName().contains(keyword) 模糊匹配，无命中提示“无匹配结果”）、4 修改（找下标后 set）、5 删除（找下标后 remove 并立刻 break，找不到提示“查无此人”）

        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> book = new ArrayList<>();
        book.add(new Contact("张三", "13800001111", "zs@xx.com"));   // 预置一条方便演示

        while (true) {
            System.out.println("====== 我的通讯录 ======");
            System.out.println("1.添加联系人  2.查看全部  3.搜索  4.修改  5.删除  6.退出");
            // TODO 1：把下面这行换成 while(true)+try-catch 的健壮输入：
            //   类型错误（InputMismatchException）提示 "输入无效，请输入整数！" 并 sc.next() 后重试；
            //   数字不在 1~6 之间也提示重试，合法了才赋值给 choice
            int choice = 6;

            if (choice == 6) {
                System.out.println("再见！");
                break;
            }

            switch (choice) {
                case 1:
                    // TODO 2：录入 姓名/电话/邮箱（电话号码用 sc.next() 读成 String），
                    //   姓名已存在（equals 判断）就提示 "已存在"，否则 add 并打印 "添加成功！"
                    System.out.println("【添加】功能待实现");
                    break;
                case 2:
                    // TODO 2：带序号遍历打印（i+1 + "." + showInfo()）；book.isEmpty() 时提示 "还没有联系人"
                    System.out.println("【查看】功能待实现");
                    break;
                case 3:
                    // TODO 3：输入关键字，用 c.getName().contains(keyword) 模糊匹配并打印所有命中项，无命中提示 "无匹配结果"
                    System.out.println("【搜索】功能待实现");
                    break;
                case 4:
                    // TODO 3：遍历找同名下标的联系人，找到用 setPhone/setEmail 更新并提示成功，找不到提示 "查无此人"
                    System.out.println("【修改】功能待实现");
                    break;
                case 5:
                    // TODO 3：遍历找到同名下标的联系人后 book.remove(i) 并打印 "删除成功"，然后立刻 break；找不到提示 "查无此人"
                    System.out.println("【删除】功能待实现");
                    break;
                default:
                    System.out.println("没有这个选项，请重新输入");
            }
        }
    }
}
