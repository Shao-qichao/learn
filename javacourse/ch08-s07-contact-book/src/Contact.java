// 标准 JavaBean：私有属性 + 无参/全参构造 + getter/setter + showInfo
public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact() { }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void showInfo() {
        System.out.println(name + "  电话：" + phone + "  邮箱：" + email);
    }
}
