/*
 * ============================================================
 *  2-6 章项目：收银台   （板块 c2s6 / 第 2 章）
 *  练习项目：ch02-s06-cashier-project
 *  目标：综合运用变量、类型、运算与 Scanner，写出一个能算账找零的收银台程序。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补上应付金额 payable = totalPrice * discount 和找零 change = pay - payable 两行计算。
        // - 用 Scanner 接收商品名、单价、数量、折扣、付款金额，替换掉写死的五个值。
        // - 按小票格式补齐单价、数量、折扣、应付金额、付款、找零六行打印，并保证代码里至少有 3 条注释。

        // 第一步：先用写死的值把计算跑通，再把它们逐个换成 Scanner 输入
        String name = "矿泉水";
        double price = 2.5;
        int count = 3;
        double discount = 0.8;
        double pay = 10.0;

        // 计算原价合计
        double totalPrice = price * count;

        System.out.println("====== 欢迎光临便利蜂 ======");
        System.out.println("商品：" + name);
        System.out.println("原价合计：" + totalPrice + " 元");
        System.out.println("==========================");
    }
}
