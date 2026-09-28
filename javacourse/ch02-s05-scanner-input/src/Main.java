import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
         System.out.print("请输入你的年龄：");
         int age=sc.nextInt();
        System.out.println("你明年 " + (age + 1) + " 岁");
       
        System.out.print("请输入你的身高：");
        double high=sc.nextDouble();
        System.out.println("你的身高是："+high);
       
        System.out.print("请输入你的姓名：");
        String name=sc.next();
        System.out.println("你好，"+name);
        
    }
}
