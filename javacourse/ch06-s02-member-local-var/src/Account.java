public class Account {
    String owner;
    double balance;

    public void deposit(double money) {
        balance = balance + money;
        System.out.println(owner + " 存入 " + money + "，余额 " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
