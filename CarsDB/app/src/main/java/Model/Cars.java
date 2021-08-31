package Model;

public class Cars {

    private int id;
    private String name;
    private String price;

    public Cars() {
    }

    public Cars(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Cars(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
