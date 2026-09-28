/*
 * ============================================================
 *  3-5 for 与 break/continue   （板块 c3s5 / 第 3 章）
 *  练习项目：ch03-s05-for-break-continue
 *  目标：掌握 for 循环的三要素写法，并会用 break 提前结束、用 continue 跳过本轮。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 在循环 1 里加 if (i == 5) { continue; }，让 5 不被打印
        // - 在循环 1 里加 if (i > 8) { break; }，让循环打印完 8 就提前结束
        // - 在循环 2 里写 if (i % 2 == 0) { sum += i; }，算出偶数和 2550

        // 循环 1：把 1~10 依次打印出来
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        // 循环 2：求 1~100 的偶数和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            // 在这里把偶数累加进 sum
        }
        System.out.println("1~100 偶数和 = " + sum);
    }
}
