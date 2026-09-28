/*
 * ============================================================
 *  8-2 ArrayList 入门   （板块 c8s2 / 第 8 章）
 *  练习项目：ch08-s02-arraylist-basics
 *  目标：会创建 ArrayList 集合并用 add、get、size 与增强 for 存取和遍历一组对象。
 * ------------------------------------------------------------
 *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行
 *  完整练习要求看同目录 README.md
 * ============================================================
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 本板块 TODO（做完一条就删掉一条对应注释）：
        // - TODO 1：再 add 一本 new Book("数据结构", 59.0)，并打印集合内容和 books.size()
        // - TODO 2：用增强 for（for (Book b : books)）遍历调用 showInfo()
        // - TODO 3：用普通 for + get(i) 打印每本书的书名

        ArrayList<String> names = new ArrayList<>();
        names.add("小明");
        names.add("小红");
        names.add("小刚");
        System.out.println(names);
        System.out.println("人数：" + names.size());

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Java入门", 69.9));
        // TODO 1：再 add 一本 new Book("数据结构", 59.0)，然后打印 books 和 books.size()

        // TODO 2：用增强 for（for (Book b : books)）遍历调用 showInfo()

        // TODO 3：用普通 for 循环 + get(i) 打印每本书的书名

        System.out.println("ArrayList 练习未完成，请补全 TODO 1~3 后重新运行");
    }
}
