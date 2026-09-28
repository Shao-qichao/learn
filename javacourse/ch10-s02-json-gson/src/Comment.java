/*
 * ============================================================
 *  10-2 JSON 解析与 Gson   （板块 c10s2 / 第 10 章）
 *  练习项目：ch10-s02-json-gson
 *  目标：亲手把评论对象“手工拼成”一段合法 JSON，理解 JSON 的
 *        键和字符串都必须用双引号、元素用逗号分隔；真实项目里
 *        这种拼字符串的活交给 Gson（章项目 CommentAnalyzer 就是）。
 *  本练习不依赖任何第三方 jar，双击 run.bat 即可运行。
 * ============================================================
 */
public class Comment {
    String name;      // 昵称
    String content;   // 评论内容
    int likes;        // 点赞数

    Comment(String name, String content, int likes) {
        this.name = name;
        this.content = content;
        this.likes = likes;
    }

    /** 把这条评论拼成一个 JSON 对象字符串 */
    public String toJson() {
        return "{"
                + "\"name\":\"" + name + "\","
                + "\"content\":\"" + content + "\","
                + "\"likes\":" + likes          // 数字不加引号
                + "}";
    }
}
