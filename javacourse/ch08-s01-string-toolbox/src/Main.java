/*
 * ============================================================
 *  8-1 String 常用方法   （板块 c8s1 / 第 8 章）
 *  练习项目：ch08-s01-string-toolbox
 *  目标：熟练使用 String 的查找、截取、替换、分割等常用方法，牢记内容比较必须用 equals。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：用 trim() 去掉 s 首尾空格并打印新内容和长度，验证 18 → 16
        // - TODO 2：用 indexOf + substring 从 s 里截取出 Java 并打印
        // - TODO 3：用 split(",") 拆分 line，打印第一段，并用 Integer.parseInt 把年龄转成 int 后打印 age + 1

        String s = " Hello Java World ";
        System.out.println("原字符串：[" + s + "] 长度：" + s.length());

        // TODO 1：用 trim() 去掉首尾空格后赋值回 s，打印 s 和它的 length()

        // TODO 2：用 indexOf("Java") 找起始下标，再用 substring(下标, 下标+4) 截取出 Java 打印

        String line = "小明,18,广州";
        // TODO 3：用 split(",") 把 line 拆成数组并打印第一段；把第二段用 Integer.parseInt 转成 int 后打印 age + 1

        System.out.println("字符串练习未完成，请补全 TODO 1~3 后重新运行");
    }
}
