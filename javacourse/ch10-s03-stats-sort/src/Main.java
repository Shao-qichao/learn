/*
 * ============================================================
 *  10-3 统计分析与数据汇总   （板块 c10s3 / 第 10 章）
 *  练习项目：ch10-s03-stats-sort  ——  Main
 *  目标：用 Comparator 给评论按点赞数排序，用 HashMap 汇总
 *        每个人的发言条数，做出“热评榜”和“活跃用户”。
 * ------------------------------------------------------------
 *  只改 src 里的文件，改完双击 run.bat 运行，要求看 README.md
 * ============================================================
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment("小明", "这首歌真好听", 12));
        list.add(new Comment("小红", "单曲循环一整天", 5));
        list.add(new Comment("小明", "副歌太上头了", 3));
        list.add(new Comment("小刚", "一般般吧", 0));

        // 按点赞数从大到小排序：compare 返回 正数 表示 a 排在 b 后面
        // b.likes - a.likes：b 比 a 大时返回正数 -> a 排后面 -> 大的在前（降序）
        list.sort(new Comparator<Comment>() {
            public int compare(Comment a, Comment b) {
                return b.likes - a.likes;
            }
        });

        System.out.println("== 热评榜（点赞从高到低）==");
        for (Comment c : list) {
            System.out.println(c);
        }

        // 用 HashMap 汇总每个人发了几条评论
        HashMap<String, Integer> talkCount = new HashMap<>();
        for (Comment c : list) {
            talkCount.put(c.name, talkCount.getOrDefault(c.name, 0) + 1);
        }
        System.out.println("== 每人发言条数 ==");
        System.out.println(talkCount);

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：把排序改成点赞数“从低到高”（提示：把 b.likes - a.likes 换成 a.likes - b.likes）
        // - TODO 2：热评榜循环里只打印 likes >= 2 的评论（用 if 跳过小刚那条 0 赞的）
        // - TODO 3：排序后 list.get(0) 就是点赞最高的，单独打印一行“今日最热：xxx”
    }
}
