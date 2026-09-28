/*
 * ============================================================
 *  4-5 章项目：成绩分析器   （板块 c4s5 / 第 4 章）
 *  练习项目：ch04-s05-score-analyzer
 *  目标：用数组把录入、遍历、求和、最值和分段统计串起来，输出一份成绩分析报告。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 计算并打印平均分、最高分、最低分（平均值用 (double) sum / scores.length，最值用打擂台法，初值取 scores[0]）
        // - 用计数器统计及格（>=60）和不及格人数，再用 if / else if 从高往低把成绩分成优秀(>=90)、良好(>=80)、及格(>=60)、不及格四段分别计数
        // - 把写死的数组改成 Scanner 录入：先输入学生人数 n，用 new int[n] 创建数组，再循环输入 n 个成绩

        int[] scores = {88, 92, 56, 77, 100};

        // 求和
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }

        System.out.println("====== 成绩分析报告 ======");
        // 遍历打印所有成绩
        System.out.print("所有成绩：");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + " ");
        }
        System.out.println();
        System.out.println("总分：" + sum);
        System.out.println("==========================");
    }
}
