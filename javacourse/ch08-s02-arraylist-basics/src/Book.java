public class Book {
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public void showInfo() {
        System.out.println("书名：" + name + "  价格：" + price);
    }
}
