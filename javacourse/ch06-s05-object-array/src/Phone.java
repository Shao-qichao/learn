public class Phone {
    private String brand;
    private double price;

    public Phone() {
        this("未知品牌", 0);
    }

    public Phone(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public void show() {
        System.out.println(brand + " 价格：" + price + " 元");
    }
}
