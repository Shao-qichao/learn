/*
 * ============================================================
 *  10-2 JSON 解析与 Gson   （板块 c10s2 / 第 10 章）
 *  练习项目：ch10-s02-json-gson  ——  Main
 *  目标：把多条评论拼成一个 JSON 数组字符串，看懂方括号、逗号、
 *        双引号的规则，体会为什么真实开发要用 Gson 自动转换。
 * ------------------------------------------------------------
 *  只改 src 里的文件，改完双击 run.bat 运行，要求看 README.md
 * ============================================================
 */
public class Main {

    /** 把若干 Comment 拼成一个 JSON 数组：[{...},{...}] */
    static String toJsonArray(Comment[] list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                sb.append(",");                 // 元素之间用逗号，第一个前面不加
            }
            sb.append(list[i].toJson());
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Comment[] list = {
            new Comment("小明", "这首歌真好听", 12),
            new Comment("小红", "单曲循环一整天", 5)
        };
        String json = toJsonArray(list);
        System.out.println("拼好的 JSON：");
        System.out.println(json);

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：在上面数组里再 new 一条你自己的评论，确认逗号、引号都正确
        // - TODO 2：给 Comment 增加一个字段 String time（如 "2024-01-01"），
        //           并在 Comment.toJson() 里把 "time":"..." 也输出出来
        // - TODO 3：把某条评论内容改成带英文双引号的话，比如他说"太棒了"，
        //           观察拼出的 JSON 是否还合法；想想要在引号前加什么符号转义（提示：\"）
    }
}
