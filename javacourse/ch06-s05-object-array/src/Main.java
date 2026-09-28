/*
 * ============================================================
 *  6-5 对象数组   （板块 c6s5 / 第 6 章）
 *  练习项目：ch06-s05-object-array
 *  目标：掌握对象数组的两步创建：先开格子再逐个 new 对象，并能遍历统计数组里的对象。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1 给 phones[0]、phones[1]、phones[2] 分别放入 new 出来的 Phone 对象
        // - TODO 2 用普通 for 循环遍历数组，调用 show() 打印每台手机的信息
        // - TODO 3 用增强 for 累加价格，输出总价和平均价格

        System.out.println("===== 对象数组练习 =====");
        Phone[] phones = new Phone[3];
        System.out.println("数组长度：" + phones.length);
        System.out.println("还没放对象时 phones[0] = " + phones[0]);

        // TODO 1：给 phones[0]、phones[1]、phones[2] 分别放入 new 出来的手机对象
        //         例如 phones[0] = new Phone("小米", 1999.0);
        // TODO 2：用普通 for 循环遍历数组，对每个元素调用 show() 打印品牌和价格
        // TODO 3：用增强 for 循环累加价格，输出总价与平均价格（注意除以数组长度要得到小数）
    }
}
