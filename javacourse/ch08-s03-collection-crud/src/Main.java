/*
 * ============================================================
 *  8-3 集合增删改查   （板块 c8s3 / 第 8 章）
 *  练习项目：ch08-s03-collection-crud
 *  目标：掌握 ArrayList 的增删改查（add/get/set/remove/contains/indexOf）并安全地按内容删除元素。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：用 add(0, "插队的") 指定位置插入，再用 set(index, 值) 修改一个元素并打印
        // - TODO 2：用 indexOf("B") 找到下标后 remove(idx) 删除，找不到就打印“查无此人”
        // - TODO 3：用 contains("C") 判断 C 是否还在集合里，打印 true/false

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("初始：" + list);

        // TODO 1：用 add(0, "插队的") 插到最前面，再用 set(1, "新名字") 改一个元素，打印 list

        // TODO 2：用 indexOf("B") 找下标后 remove(idx) 删除 B（找不到打印 "查无此人"），再打印 list

        // TODO 3：用 contains("C") 判断 C 是否还在，打印判断结果

        System.out.println("CRUD 练习未完成，请补全 TODO 1~3 后重新运行");
    }
}
