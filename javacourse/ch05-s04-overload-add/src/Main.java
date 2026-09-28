/*
 * ============================================================
 *  5-4 方法重载   （板块 c5s4 / 第 5 章）
 *  练习项目：ch05-s04-overload-add
 *  目标：理解方法重载：同名方法只要参数列表不同，调用时会由 Java 按实参自动选择对应版本。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - 补全 Tools.add(double a, double b)，返回两数之和（注意返回类型是 double）
        // - 补全 Tools.add(int a, int b, int c)，返回三个数之和
        // - 在 main 中依次打印 Tools.add(1.5, 2.5) 和 Tools.add(1, 2, 3)，观察 Java 分别自动选择了哪个版本

        System.out.println(Tools.add(1, 2));
    }
}
