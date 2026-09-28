/*
 * ============================================================
 *  4-2 遍历与 length   （板块 c4s2 / 第 4 章）
 *  练习项目：ch04-s02-array-loop-length
 *  目标：学会用 for 配合 arr.length 遍历数组，并分得清普通 for 和增强 for 的用途。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 在遍历循环里把每个元素加 5（arr[i] = arr[i] + 5），循环后再遍历一次打印新数组
        // - 用增强 for（for (int x : arr)）再打印一遍数组，观察它拿不到下标
        // - 写一行注释说明为什么条件是 i < arr.length 而不是 i <= arr.length

        int[] arr = {10, 20, 30, 40, 50};

        // 普通 for：靠下标遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("数组长度：" + arr.length);
    }
}
