/*
 * ============================================================
 *  9-4 哈希表 HashSet/HashMap   （板块 c9s4 / 第 9 章）
 *  练习项目：ch09-s04-hash-table
 *  目标：用 HashMap 的 getOrDefault 统计字符出现次数，
 *        再用“空间换时间”的哈希表一趟解决两数之和（O(n)）。
 * ------------------------------------------------------------
 *  只改本文件，改完双击 run.bat 运行，完整要求看 README.md
 * ============================================================
 */
import java.util.HashMap;

public class Main {

    /** 统计每个字符出现次数，并找出出现最多的字符 */
    static void countChars(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 键已有就 +1，没有就当 0 再 +1 —— 统计频次的固定写法
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        System.out.println(s + " 的字符频次：" + count);

        // TODO 2 在下面补：遍历 count，找出次数最多的字符并打印
    }

    /**
     * 哈希表版两数之和（数组不要求有序）：
     * 边走边把“值 -> 下标”存进 map，每到一个数就问 target-它 在不在 map 里。
     */
    static void twoSumHash(int[] a, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();   // 值 -> 下标
        for (int i = 0; i < a.length; i++) {
            int need = target - a[i];
            if (seen.containsKey(need)) {
                System.out.println("目标 " + target + "：下标 "
                        + seen.get(need) + " 和 " + i
                        + "（" + need + " + " + a[i] + "）");
                return;
            }
            seen.put(a[i], i);
        }
        System.out.println("目标 " + target + "：没找到");
    }

    public static void main(String[] args) {
        countChars("banana");          // a 出现 3 次，最多
        countChars("hello java");

        int[] nums = {2, 7, 11, 15, 3};
        twoSumHash(nums, 9);
        twoSumHash(nums, 18);
        twoSumHash(nums, 100);

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：再调用一次 countChars，传一个你喜欢的字符串
        // - TODO 2：在 countChars 里用 for 遍历 count.entrySet()，找出次数最多的字符打印
        // - TODO 3：用 // 注释回答：哈希表版两数之和为什么是 O(n)？牺牲了什么换来的？
        // 答：
    }
}
