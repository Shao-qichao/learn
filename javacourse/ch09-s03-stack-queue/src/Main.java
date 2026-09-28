/*
 * ============================================================
 *  9-3 链表、栈与队列   （板块 c9s3 / 第 9 章）
 *  练习项目：ch09-s03-stack-queue
 *  目标：用官方推荐的 Deque(ArrayDeque) 当栈，判断括号串是否有效；
 *        再用它当队列模拟排队，记住“栈后进先出、队列先进先出”。
 * ------------------------------------------------------------
 *  只改本文件，改完双击 run.bat 运行，完整要求看 README.md
 * ============================================================
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    /** 判断括号是否成对且正确嵌套：遇左括号压“对应的右括号”，遇右括号弹栈比对 */
    static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                // 来了个右括号：栈空说明没有左括号配它；弹出来对不上也无效
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();      // 全部配对完，栈应该正好清空
    }

    public static void main(String[] args) {
        String[] cases = {"()", "([])", "{[()]}", "([)]", "(()", ")("};
        for (String c : cases) {
            System.out.println(c + "  ->  " + (isBalanced(c) ? "有效(是)" : "无效(否)"));
        }

        // 本板块 TODO（做完一条删一条注释）：
        // - TODO 1：往 cases 里再加两个字符串：一个你认为有效、一个无效，运行核对
        // - TODO 2：空字符串 "" 算不算有效？先猜，再把它加进 cases 验证并解释原因
        // - TODO 3：用下面这段队列代码模拟排队，取消注释运行，观察谁先被服务
        // Deque<String> queue = new ArrayDeque<>();
        // queue.offer("小明"); queue.offer("小红"); queue.offer("小刚");
        // while(!queue.isEmpty()){ System.out.println("服务：" + queue.poll()); }
    }
}
