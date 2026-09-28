public class Book {
    private String title;
    private String author;
    private double price;

    public Book() {
        this("未命名", "佚名", 0);
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        setPrice(price);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("价格不能为负数，已按 0 处理");
            this.price = 0;
        }
    }

    public void showInfo() {
        System.out.println("《" + title + "》 作者：" + author + " 价格：" + price + " 元");
    }
}
