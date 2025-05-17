import java.util.HashMap;

public class Customer {
    //поля и конструктор класса
    int ID;
    String name;
    HashMap<Product, Integer> listOfProduct = new HashMap<>();
    private static int quantityOfCustomers;

    public Customer(String name) {
        this.ID = quantityOfCustomers++;
        this.name = name;
    }
    //метод покупки конкретным лицом, хранящий все покупки данного лица. используется в методах покупки товара покупателем
    public void buy(Product product, int quantity){
        listOfProduct.merge(product, quantity, Integer::sum);
    }
    //метод для проверки действительно ли куплен товар покупателем. используется в методе возврата товара
    public boolean isBuy(Product product, int quantity){
        if(listOfProduct.containsKey(product) && listOfProduct.get(product) >= quantity){
            return true;
        }
        return false;
    }

}
