import java.util.Objects;

public class Product {
    int ID;
    String name;
    int price;
    private static int quantityOfProduct = 0;

    public Product(String name, int price) {
        this.ID = quantityOfProduct++;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return ID == product.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return name + " " + ID;
    }
}
