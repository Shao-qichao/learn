/*
 * ============================================================
 *  10-1 文件读写 BufferedReader   （板块 c10s1 / 第 10 章）
 *  练习项目：ch10-s01-file-io
 *  目标：用 FileWriter 写文件、用 BufferedReader 逐行读文件，
 *        掌握 try-with-resources 自动关闭，以及 readLine 返回 null 表示读完。
 * ------------------------------------------------------------
 *  只改本文件，改完双击 run.bat 运行，完整要求看 README.md
 *  notes.txt 会在运行目录自动生成，不用自己建
 * ============================================================
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static final String FILE = "notes.txt";

    /** 把 3 行文字写进文件 */
    static void writeFile() throws IOException {
        // try(...) 里声明的流会在结束时自动 close()，哪怕中途出异常也不泄漏
        try (FileWriter fw = new FileWriter(FILE)) {   // 第二个参数 true = 追加
            fw.write("第一行：学 Java 的第 10 章\n");
            fw.write("第二行：今天练习文件读写\n");
            fw.write("第三行：读完记得关闭文件\n");
        }
    }

    /** 逐行读回文件，统计行数；readLine() 返回 null 表示读到文件末尾 */
    static void readFile() throws IOException {
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                System.out.println("第 " + lines + " 行：" + line);
            }
        }
        System.out.println("一共 " + lines + " 行");
    }

    public static void main(String[] args) throws IOException {
        writeFile();
        System.out.println("== 第一次写入并读取 ==");
        readFile();

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：把 writeFile 里 new FileWriter(FILE) 改成 new FileWriter(FILE, true)，
        //           连续运行两次（run.bat 跑两遍），观察 notes.txt 内容是被覆盖还是追加
        // - TODO 2：在 readFile 里加一个变量统计所有行的“总字符数”（每行行长相加）并打印
        // - TODO 3：用 // 注释回答：为什么要用 try(...) 把流包起来，而不是自己写 close()？
        // 答：
    }
}
