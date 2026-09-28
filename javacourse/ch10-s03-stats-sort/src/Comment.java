/*
 * ============================================================
 *  10-3 统计分析与数据汇总   （板块 c10s3 / 第 10 章）
 *  练习项目：ch10-s03-stats-sort  ——  评论数据类
 * ============================================================
 */
public class Comment {
    String name;
    String content;
    int likes;

    Comment(String name, String content, int likes) {
        this.name = name;
        this.content = content;
        this.likes = likes;
    }

    public String toString() {
        return name + "（赞 " + likes + "）：" + content;
    }
}
