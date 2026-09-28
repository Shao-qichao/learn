/*
 * ============================================================
 *  5-3 参数与返回值   （板块 c5s3 / 第 5 章）
 *  练习项目：ch05-s03-params-return
 *  目标：掌握带参数与带返回值方法的定义和调用，会写形参、实参并用 return 把结果交回调用处。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补全 Tools.add(int a, int b) 的方法体，让它返回两个数的和（把 return 0 改成正确结果）
        // - 补全 Tools.isAdult(int age)：age 大于等于 18 返回 true，否则返回 false
        // - 在 main 中把 add 的返回值用 int 变量接住再打印，并加一句：当 Tools.isAdult(20) 成立时打印“可以进入”

        Tools.printWelcome("小明", 3);
        Tools.printWelcome("小红", 1);
        System.out.println(Tools.add(3, 5));
    }
}
