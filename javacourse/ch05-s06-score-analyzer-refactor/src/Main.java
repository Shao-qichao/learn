/*
 * ============================================================
 *  5-6 章项目：重构+计算器   （板块 c5s6 / 第 5 章）
 *  练习项目：ch05-s06-score-analyzer-refactor
 *  目标：用方法把成绩分析器重构：录入、打印、求和、平均、最高分、分段统计各写一个方法各司其职。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补全 ScoreTools.getSum 与 getAvg，分别返回总分和平均分（平均分要用 double 计算，避免整数相除丢小数）
        // - 补全 ScoreTools.getMax，用循环遍历数组找出最大值并返回
        // - 补全 ScoreTools.printLevelCount，统计并打印 90 以上、80~89、60~79、60 以下四个分数段的人数

        int[] scores = ScoreTools.inputScores();
        System.out.print("所有成绩：");
        ScoreTools.printScores(scores);
        System.out.println("总分：" + ScoreTools.getSum(scores));
        System.out.println("平均分：" + ScoreTools.getAvg(scores));
        System.out.println("最高分：" + ScoreTools.getMax(scores));
        ScoreTools.printLevelCount(scores);
    }
}
