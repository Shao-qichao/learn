import java.util.Scanner;

public class ScoreTools {
    public static int[] inputScores() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入成绩个数：");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第 " + (i + 1) + " 个成绩：");
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    public static void printScores(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int getSum(int[] arr) {
        return 0;
    }

    public static double getAvg(int[] arr) {
        return 0;
    }

    public static int getMax(int[] arr) {
        return 0;
    }

    public static void printLevelCount(int[] arr) {
    }
}
