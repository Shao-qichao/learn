/*
 * ============================================================
 *  9-1 时间复杂度与空间复杂度   （板块 c9s1 / 第 9 章）
 *  练习项目：ch09-s01-big-o
 *  目标：用“循环执行次数计数器”亲手感受 O(n) 与 O(n²) 的差别，
 *        看懂只占固定变量的 O(1) 空间。
 * ------------------------------------------------------------
 *  只改本文件，改完双击 run.bat 运行，完整要求看 README.md
 * ============================================================
 */
public class Main {

    /** O(n)：单层循环，数据量翻几倍，执行次数就翻几倍 */
    static int countLinear(int n) {
        int times = 0;                 // 计数器
        for (int i = 0; i < n; i++) {
            times++;                   // 每进一次循环 +1
        }
        return times;
    }

    /** O(n²)：双层循环嵌套，数据量翻倍，执行次数变 4 倍 */
    static int countQuadratic(int n) {
        int times = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                times++;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        int[] sizes = {5, 10, 20};
        System.out.println(" n | O(n)执行次数 | O(n^2)执行次数");
        System.out.println("---|--------------|----------------");
        for (int n : sizes) {
            System.out.printf("%2d | %12d | %14d%n", n, countLinear(n), countQuadratic(n));
        }

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：把上面数组里的 20 改成 30，先口算 O(n^2) 会是多少，再运行核对
        // - TODO 2：在 countLinear 里再声明一个变量 sum，把每次循环的 i 累加进去并在 main 打印 sum（只多了一个变量，仍是 O(1) 空间）
        // - TODO 3：用 // 注释回答：为什么 O(3n^2+2n+1) 可以简写成 O(n^2)？写在下面这行旁边
        // 答：
        System.out.println("观察：n 从 5 到 20（4 倍），O(n) 变 4 倍，O(n^2) 变 16 倍。");
    }
}
