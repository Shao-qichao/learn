/*
 * ============================================================
 *  9-2 双指针与数组操作   （板块 c9s2 / 第 9 章）
 *  练习项目：ch09-s02-two-pointers
 *  目标：用“对撞指针”在有序数组里一趟找出和等于目标值的两个数，
 *        对比暴力双重循环，体会 O(n) 为什么比 O(n^2) 快。
 * ------------------------------------------------------------
 *  只改本文件，改完双击 run.bat 运行，完整要求看 README.md
 * ============================================================
 */
public class Main {

    /**
     * 有序（从小到大）数组里找两个数，使 a[left] + a[right] == target。
     * 找到就打印这一对；找不到打印“没找到”。
     * 关键：left 从最左、right 从最右，两个指针往中间走，最多走 n 趟 = O(n)。
     */
    static void twoSumSorted(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int sum = a[left] + a[right];
            if (sum == target) {
                System.out.println("找到：a[" + left + "]=" + a[left]
                        + " + a[" + right + "]=" + a[right] + " = " + target);
                return;                       // 找到了，结束方法
            } else if (sum < target) {
                left++;                       // 和太小，左指针右移让和变大
            } else {
                right--;                      // 和太大，右指针左移让和变小
            }
        }
        System.out.println("目标 " + target + "：没找到这样的两个数");
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11, 13};   // 必须是有序的！
        twoSumSorted(nums, 12);
        twoSumSorted(nums, 20);
        twoSumSorted(nums, 6);
        twoSumSorted(nums, 100);

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：再加一行调用，找一组你自己定的 target，确认结果
        // - TODO 2：在 while 循环开头加一行 System.out.println(left+","+right);
        //           运行后数一数两个指针一共移动了几次（体会“一趟”O(n)）
        // - TODO 3：用 // 注释回答：为什么这个方法要求数组必须有序？写在下面
        // 答：
    }
}
