/*
 * ============================================================
 *  5-5 数组作为参数   （板块 c5s5 / 第 5 章）
 *  练习项目：ch05-s05-array-param
 *  目标：掌握数组作为参数与返回值，理解方法内修改数组元素会改变外部传入的同一个原数组。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补全 Tools.addFive(int[] arr)：用 for 循环给数组每个元素加 5（在方法内直接改 arr[i]，不要改方法签名）
        // - 补全 Tools.makeArray(int n)：新建长度为 n 的数组，填入 1 到 n 后返回（返回类型是 int[]）
        // - 在 main 中调用 Tools.addFive(scores) 后再次 Tools.printArray(scores)，观察原数组是否被改变；再打印 Tools.makeArray(5)

        int[] scores = {88, 92, 56};
        Tools.printArray(scores);
        System.out.println("总分：" + Tools.getSum(scores));
    }
}
