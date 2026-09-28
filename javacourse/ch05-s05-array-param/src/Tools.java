public class Tools {
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

    public static void addFive(int[] arr) {
        // TODO: 用 for 循环给每个元素加 5，直接改 arr[i]
    }

    public static int[] makeArray(int n) {
        // TODO: 新建长度为 n 的数组并填入 1 到 n 后返回
        return new int[n];
    }
}
